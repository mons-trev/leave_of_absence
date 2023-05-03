package me.april.repository;

import lombok.RequiredArgsConstructor;
import me.april.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public long saveUser(User user) {
        em.persist(user); // jpa가 저장하는 로직
        return user.getId();
    }

    public User findUser(long id) { //한 명 조회
        return em.find(User.class, id);
    }

    public List<User> findAll() { //모두 조회
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    public List<User> findByName(String name) { //이름으로 조회
        return em.createQuery("select u from User u where u.user_name =: name")
                .setParameter("name", name)
                .getResultList();
    }

    public List<User> findByUser_Id(String id) {
        return em.createQuery("select u from User u where u.user_id =: id")
                .setParameter("id", id)
                .getResultList();
    }
}
