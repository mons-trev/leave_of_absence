package me.april.repository;

import lombok.RequiredArgsConstructor;
import me.april.domain.Team;
import me.april.domain.User;
import me.april.domain.User_Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class User_TeamRepository {

    private final EntityManager em;

    public long save (User_Team user_team) {
        em.persist(user_team);
        return user_team.getId();
    }
}
