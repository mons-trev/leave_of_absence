package me.april.repository;

import me.april.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

//import static org.junit.Assert.*;

@SpringBootTest
public class userRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testUser() throws Exception {
        User user = new User();
        user.setUser_id("jieun2");
        user.setUser_pw("0000");
        user.setUser_name("me");

        long saveUser_pk = userRepository.saveUser(user);
        User finduser = userRepository.findUser(user.getId());

        Assertions.assertThat(finduser.getUser_name()).isEqualTo(user.getUser_name());
    }
}