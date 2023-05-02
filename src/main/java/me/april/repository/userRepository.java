package me.april.repository;

import me.april.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class userRepository {

    @PersistenceContext
    private EntityManager em;

    public long saveUser(User user) {
        em.persist(user);
        return user.getId();
    }

    public User findUser(long id) {
        return em.find(User.class, id);
    }
}
