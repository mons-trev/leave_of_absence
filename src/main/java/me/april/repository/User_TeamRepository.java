package me.april.repository;

import lombok.RequiredArgsConstructor;
import me.april.domain.Team;
import me.april.domain.User;
import me.april.domain.User_Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class User_TeamRepository {

    private final EntityManager em;

    public long save (User_Team user_team) {
        em.persist(user_team);
        return user_team.getId();
    }

    public List<User_Team> check (User_Team user_team) {
        long userId= user_team.getUser().getId();
        long teamId= user_team.getTeam().getId();
        return em.createQuery("select ut from User_Team ut where ut.user_id=:userId & ut.team_id=:teamId")
                .setParameter("userId",userId)
                .setParameter("teamId",teamId)
                .getResultList();
    }
}
