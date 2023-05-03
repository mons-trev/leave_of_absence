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
    void makeTeam() {
        User user= userService.findOne(1L);
        Team team= Team.builder()
                .team_name("teamtest")
                .team_mem_cnt(0)
                .team_manager(user)
                .team_pw("0000").build();

        Long teamid= teamService.makeTeam(user, team);

        assertEquals(teamid,team.getId());
    }
    @Test
    void 이름중복된팀() {
        User user= userService.findOne(1L);
        Team team= Team.builder()
                .team_name("중복팀")
                .team_mem_cnt(0)
                .team_manager(user)
                .team_pw("0000").build();

        Long teamid= teamService.makeTeam(user, team);
        Team team2= Team.builder()
                .team_name("중복팀")
                .team_mem_cnt(0)
                .team_manager(user)
                .team_pw("0000").build();
        try{
            teamService.makeTeam(user,team);
        } catch (IllegalStateException e) {
            return;
        }
        fail();
    }

    @Test
    void joinTeam() {
        Team team= Team.builder()
                .team_name("teamtest")
                .team_mem_cnt(0)
                .team_manager(userService.findOne(1L))
                .team_pw("0000").build();

        Long teamid= teamService.makeTeam(userService.findOne(1L), team);
        User user= userService.findOne(4L);
        String pw= "0000";
        String teamName= "teamtest";
        Long teamId= teamService.joinTeam(user, pw, teamName);
    }

    @Test
    void 비번달라서가입불가() {
        User user= userService.findOne(4L);
        Team team= Team.builder()
                .team_name("teamtest")
                .team_mem_cnt(0)
                .team_manager(userService.findOne(1L))
                .team_pw("0000").build();

        Long teamid= teamService.makeTeam(userService.findOne(1L), team);
        String pw= "0001";
        String teamName= "teamtest";
        try {
            Long teamId = teamService.joinTeam(user, pw, teamName);
        } catch (IllegalStateException e) {
            return;
        }
        fail();
    }
}