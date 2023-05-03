package me.april.service;

import me.april.domain.Team;
import me.april.domain.User;
import me.april.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired UserService userService;
    @Test
    @Rollback(false)
    void makeTeam() {
        Team team= new Team();
        User user= userService.findOne(1L);
        team.setTeam_name("첫팀");
        team.setTeam_pw("0000");
        team.setTeam_manager(user);
        team.setTeam_mem_cnt(0);
        Long teamid= teamService.makeTeam(user, team);

        assertEquals(teamid,team.getId());
    }

    @Test
    @Rollback(false)
    void joinTeam() {
        Team team= new Team();
        User user= userService.findOne(4L);
        String pw= "0000";
        String teamName= "첫팀";
        Long teamId= teamService.joinTeam(user, pw, teamName);
    }
}