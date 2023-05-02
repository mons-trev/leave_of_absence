package me.april.repository;

import me.april.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class userRepository {

    @PersistenceContext
    private EntityManager em;

    public int saveUser(User user) {
        em.persist(user);
        return user.getUser_pk();
    }

    public User findUser(int user_pk) {
        return em.find(User.class, user_pk);
    }
}
