package me.april.service;

import me.april.domain.User;
import me.april.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional //롤백 하기 위함!
class UserServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        User user = new User();
        user.setUser_id("test1");
        user.setUser_pw("0000");
        user.setUser_name("지은");

        Long saveId= userService.join(user);

        assertEquals(user, userRepository.findUser(saveId));
    }
    @Test
    public void 회원아이디중복() throws Exception {
        User user= new User();
        user.setUser_id("kim");
        user.setUser_pw("0");
        user.setUser_name("1");

        User user2= new User();
        user2.setUser_id("kim");
        user2.setUser_pw("0");
        user2.setUser_name("1");

        Long saveId= userService.join(user);
        try {
            Long saveId2 = userService.join(user2);
        } catch (IllegalStateException e) {
            return;
        }
        fail("예외가 발생해야한다.");
    }

    @Test
    public void 회원이름중복() throws Exception {
        User user= new User();
        user.setUser_id("kim1");
        user.setUser_pw("0");
        user.setUser_name("1");

        User user2= new User();
        user2.setUser_id("kim2");
        user2.setUser_pw("0");
        user2.setUser_name("1");

        Long saveId= userService.join(user);

        try {
            Long saveId2 = userService.join(user2);
        } catch (IllegalStateException e) {
            return;
        }

        fail("예외가 발생해야한다");
    }
}