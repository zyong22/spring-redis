package shop.mtcoding.bank.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsernameAndPassword_test(){
        String username = "ssar";
        String password = "1234";

        User user = userRepository.findByUsernameAndPassword(username, password);
        System.out.println(user.getUsername());
    }

    @Test
    public void save_test(){
        // given (데이터준비)
        String username = "haha";
        String password = "1234";
        String email = "haha@nate.com";
        String fullname = "하하";

        // when
        userRepository.save(username, password, email, fullname);
    }
}
