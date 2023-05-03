package me.april.service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.april.domain.User;
import me.april.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    public Long join(User user) {
        validateDuplicateUserId(user);
        validateDuplicateUsername(user);
        userRepository.saveUser(user);
        return user.getId();
    }
    //예외 처리
    public void validateDuplicateUsername(User user) {
        List<User> find = userRepository.findByName(user.getUser_name());
        if(!find.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        }
    }
    public void validateDuplicateUserId(User user) {
        List<User> find = userRepository.findByUser_Id(user.getUser_id());
        if(!find.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findOne(Long userId) {
        return userRepository.findUser(userId);
    }
}
