import config.JDBCConnect;
import config.Properties;
import repository.impl.OrderRepoImpl;

public class Main {
    public static void main(String[] args) {
        OrderRepoImpl or = new OrderRepoImpl(new JDBCConnect(new Properties("C:\\Test\\JSONTest.json")));
        System.out.println(or.findById(19L));
    }
}
