import config.JDBCConnect;
import config.Properties;
import repository.impl.OrderRepoImpl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {
        OrderRepoImpl or = new OrderRepoImpl(new JDBCConnect(new Properties("C:\\Test\\JSONTest.json")));
       or.createOrder(1L,new Date(new SimpleDateFormat("dd-MM-yyyy HH:mm")
               .parse("14-02-2024 01:23")
               .getTime()),3L,1);
    }
}
