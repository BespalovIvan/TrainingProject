import config.JDBCConnect;
import config.Properties;
import entity.Order;
import repository.impl.OrderRepoImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderRepoImpl or = new OrderRepoImpl(new JDBCConnect(new Properties("C:\\Test\\JSONTest.json")));
        List<Order> orders = or.findByUserId(5L);
        for (Order o : orders){
            System.out.println(o);
        }
    }
}
