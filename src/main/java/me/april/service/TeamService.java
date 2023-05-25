package me.april.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.april.domain.Team;
import me.april.domain.User;
import me.april.domain.User_Team;
import me.april.repository.TeamRepository;
import me.april.repository.User_TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {
    public final TeamRepository teamRepository;
    public final User_TeamRepository user_teamRepository;
    public Long makeTeam(User user, Team team) {  //팀 만들기
        validateDuplicateName(team.getTeam_name()); // ERROR : 이미 있는 이름인지 확인
        team.setTeam_mem_cnt(team.getTeam_mem_cnt()+1);
        teamRepository.save(team);
        User_Team user_team= new User_Team();
        user_team.setTeam(team);
        user_team.setUser(team.getTeam_manager());
        user_teamRepository.save(user_team);
        return team.getId();
    }

    public Long joinTeam(User user, String pw, String teamName) { //팀에 가입하기 파라미터 : 유저, 유저가 입력한 비밀번호, 유저가 선택한 팀
        Team team= teamRepository.findTeamByName(teamName).get(0);
        User_Team user_team = new User_Team();
        user_team.setTeam(team);
        user_team.setUser(user);
        if(!user_teamRepository.check(user_team).isEmpty()) { //이미 가입한 팀인지 확인
            throw new IllegalStateException("이미 가입된 팀입니다.");
        }
        validateTeamPw(team, pw);//비밀번호 검증
        team.setTeam_mem_cnt(team.getTeam_mem_cnt()+1); // 팀 멤버 한명 늘리기
        user_teamRepository.save(user_team);
        return user_team.getId();
    }
    @Transactional(readOnly = true) //팀 목록 조회
    public List<Team> findTeams() {
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true) //팀 이름으로 한 개 팀 조회하기
    public List<Team> findTeamsByName(String name) {
        return teamRepository.findTeamByName(name);
    }
    public void validateDuplicateName(String name) {//팀 이름 중복 확인하기
        List<Team> team= teamRepository.findTeamByName(name);
        if(!team.isEmpty()) throw new IllegalStateException("이미 존재하는 팀명입니다.");
    }
    public void validateTeamPw(Team team, String pw) {//팀원 가입 검증
        if(!team.getTeam_pw().equals(pw)) {
            throw new IllegalStateException("팀 비밀번호와 일치하지 않습니다.");
        }
    }
}
