package repository.impl;

import com.example.trainingProject.config.JDBCConnect;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.impl.UserRepoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UserRepoImplTest {
    @Test
    public void testFindBetween() {
        UserRepoImpl userRepo = new UserRepoImpl(new JDBCConnect(new Properties("C:\\Test\\JSONTest.json")));
        List<User> users = userRepo.findBetween(1L,2L);
        List<User> expected = new ArrayList<>();
        expected.add(new User(1L,"Ivan","bespalovivan@mail.ru"));
        expected.add(new User(2L,"Kirill","melnikKirill@yandex.ru"));
        assertThat(users).isEqualTo(expected);
    }
}