package repository;

import entity.Order;
import entity.Product;
import entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepo {
     List<Order> findAll();
     Order findById(Long id);
     void createOrder(User user, List<Product> products, LocalDateTime createOrder);
     void deleteOrder(Integer id);
}
