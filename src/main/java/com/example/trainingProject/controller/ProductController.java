package com.example.trainingProject.controller;

import com.example.trainingProject.config.MyUserDetails;
import com.example.trainingProject.dto.OrderProductDto;
import com.example.trainingProject.dto.ProductDto;
import com.example.trainingProject.service.DBFileService;
import com.example.trainingProject.service.OrderService;
import com.example.trainingProject.service.ProductService;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final OrderService orderService;
    private final DBFileService dbFileService;

    public ProductController(ProductService productService, OrderService orderService, DBFileService dbFileService) {
        this.productService = productService;
        this.orderService = orderService;
        this.dbFileService = dbFileService;
    }

    @GetMapping("/products")
    public String findAll(@AuthenticationPrincipal MyUserDetails user, Model model) {
        List<ProductDto> products = productService.findAll();
        model.addAttribute("products", products);
        if (user == null) {
            return "productListForView";
        }
        return "products";
    }

    @GetMapping("/products-order/{id}")
    public String findProductByOrder(@PathVariable("id") Long orderId, Model model) {
        List<OrderProductDto> productsDto = productService.findProductByOrderId(orderId);
        model.addAttribute("order", orderService.findById(orderId));
        model.addAttribute("productsOrder", productsDto);
        return "productsOrder";
    }

    @PostMapping("/add-product")
    public String addProductToOrder(@AuthenticationPrincipal MyUserDetails user, @RequestParam("id") Long productId) {
        productService.addProductToOrder(user.getUserId(), productId);
        return "redirect:/products";
    }

    @PostMapping("/delete-product/{order_id}/{product_id}")
    public String deleteProductFromOrder(@PathVariable("order_id") Long orderId,
                                         @PathVariable("product_id") Long productId) {
        productService.deleteProductFromOrder(orderId, productId);
        return "redirect:/products-order/{order_id}";
    }

    @GetMapping(value = "/file", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Object> getImage(@RequestParam(name = "id", required = false) Long productId) {
        HttpHeaders headers = new HttpHeaders();
        byte[] result = dbFileService.getFileByProductId(productId).getData();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

}
