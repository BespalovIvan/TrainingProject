package repository;

import entity.Order;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepo {
     List<Order> findAll();
     Order findById(Long id);
     List<Order> findByUserId(Long id);
     Long createOrder(Long userId, LocalDateTime dateTime, Long productId, Integer countProducts);
     void deleteOrder(Integer id);
}
