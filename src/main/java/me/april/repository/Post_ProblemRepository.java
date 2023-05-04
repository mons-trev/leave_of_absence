package me.april.repository;

import lombok.RequiredArgsConstructor;
import me.april.domain.Post_Problem;
import me.april.domain.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class Post_ProblemRepository {
    private final EntityManager em;

    public long save(Post_Problem post_problem) { // 저장
        em.persist(post_problem);
        return post_problem.getId();
    }

    public List<Post_Problem> findProblemsByTeam(Team team) { // 팀별 문제 전체 조회
        Long teamId=team.getId();
        List<Post_Problem> problems=
                em.createQuery("select p from Post_Problem p where p.team_pk=:teamId")
                .setParameter("teamId", teamId)
                .getResultList();
        return problems;
    }

    public Post_Problem findProblemById(long id) { // 문제 하나 조회
        return (Post_Problem) em.createQuery("select p from Post_Problem p where p.id=:id")
                .setParameter("id",id)
                .getSingleResult();
    }


}
