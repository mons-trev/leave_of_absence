package me.april.service;

import me.april.domain.Post_Problem;
import me.april.domain.Team;
import me.april.domain.User;
import me.april.repository.Post_ProblemRepository;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class Post_ProblemServiceTest {

    @Autowired
    public Post_ProblemService post_problemService;
    @Autowired
    public Post_ProblemRepository post_problemRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public TeamService teamService;

    @Test
    void registerProblem() {
        User user= new User();
        user.setUser_id("kim1");
        user.setUser_pw("0");
        user.setUser_name("1");

        userService.join(user);
        Team team= Team.builder()
                .team_name("teamtest")
                .team_mem_cnt(0)
                .team_manager(user)
                .team_pw("0000").build();
        teamService.makeTeam(user, team);

        Post_Problem post_problem=new Post_Problem();

        post_problem.setDate(LocalDateTime.now());
        post_problem.setName("test1");
        post_problem.setSuccess_percent(0);
        post_problem.setTeam(team);
        post_problem.setUser(user);

        long id = post_problemService.registerProblem(post_problem);

        assertEquals(id, post_problem.getId());
    }

    @Test
    void findProblems() {
    }

    @Test
    void findOne() {
    }
}