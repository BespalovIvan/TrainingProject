package repository;

import entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderRepo {
     List<Order> findAll();
     Order findById(Long id);
     List<Order> findByUserId(Long id);
     void createOrder(Long userId, Date dateTime, Long productId, Integer countProducts);
     void deleteOrder(Integer id);
}
