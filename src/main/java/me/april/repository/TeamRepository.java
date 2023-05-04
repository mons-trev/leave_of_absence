package me.april.repository;

import lombok.RequiredArgsConstructor;
import me.april.domain.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeamRepository {

    private final EntityManager em;

    public long save(Team team) {
        em.persist(team);
        return team.getId();
    }

    public List<Team> findAll() {
        return em.createQuery("select t from Team t")
                .getResultList();
    }
    public List<Team> findTeamByName(String name) {
        return  em.createQuery("select t from Team t where t.team_name =: name")
                .setParameter("name", name)
                .getResultList();
    }
}
