package me.april.repository;

import me.april.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

//import static org.junit.Assert.*;

@SpringBootTest
public class userRepositoryTest {

    @Autowired userRepository userRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testUser() throws Exception {
        User user = new User();
        user.setUser_id("jieun");
        user.setUser_pw("0000");
        user.setUser_name("me");

        int saveUser_pk = userRepository.saveUser(user);
        User finduser = userRepository.findUser(user.getUser_pk());

        Assertions.assertThat(finduser.getUser_name()).isEqualTo(user.getUser_name());
    }
}