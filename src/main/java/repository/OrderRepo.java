package repository;

import entity.Order;
import entity.OrderStatus;
import entity.Product;
import entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepo {
     List<Order> FindAll();
     Order findById(Integer id);
     void createOrder(OrderStatus status, User user, List<Product> products, LocalDateTime createOrder);
     void deleteOrder(Integer id);
}
