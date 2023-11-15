package repository;

import entity.Product;

import java.util.List;

public interface ProductRepo {
    List<Product> findAll();
    Product findById(Integer id);
    void createProduct (String name, Double price);
    void updateProduct(Integer id, String name, Double price);
    void deleteProductById(Integer id);
}
