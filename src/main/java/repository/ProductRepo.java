package repository;

import entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepo {
    List<Product> findAll();
    Product findById(Integer id);
    void createProduct (String name, BigDecimal price);
    void updateProduct(Integer id, String name, BigDecimal price);
    void deleteProductById(Integer id);
}
