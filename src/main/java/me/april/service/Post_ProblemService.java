package me.april.service;

import lombok.RequiredArgsConstructor;
import me.april.domain.Post_Problem;
import me.april.domain.Team;
import me.april.domain.User;
import me.april.repository.Post_ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Post_ProblemService {

    public final Post_ProblemRepository post_problemRepository;

    public long registerProblem(Post_Problem problem) { //문제 등록
        return post_problemRepository.save(problem);
    }
    @Transactional(readOnly = true) //팀별 문제 조회
    public List<Post_Problem> findProblems(Team team) {
        return post_problemRepository.findProblemsByTeam(team);
    }

    @Transactional(readOnly = true) //문제 하나 조회
    public Post_Problem findOne(long id) {
        return post_problemRepository.findProblemById(id);
    }
}
