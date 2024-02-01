package repository;

import entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepo {
    List<Product> findAll();
    Product findById(Long id);
    void createProduct (String name, BigDecimal price);
    void updateProduct(Long id, String name, BigDecimal price);
    void deleteProductById(Long id);
}
