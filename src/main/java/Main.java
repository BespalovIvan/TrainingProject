import config.JDBCConnect;
import config.Properties;
import repository.impl.OrderRepoImpl;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        OrderRepoImpl or = new OrderRepoImpl(new JDBCConnect(new Properties("C:\\Test\\JSONTest.json")));
        or.createOrder(2L,
                LocalDateTime.of(2024, 2, 19, 22, 1, 23), 2L, 1);

    }
}
