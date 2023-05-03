package me.april.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String team_name;

    private int team_mem_cnt;

    private String team_pw;

    @ManyToOne
    @JoinColumn(name = "team_manager") // id로 join
    private User team_manager; // join 하는 것은 user

    @OneToMany(mappedBy = "team")
    private List<User_Team> users = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Post_Problem> problems = new ArrayList<>();

    @Builder
    public Team(String team_name, User team_manager, int team_mem_cnt, String team_pw) {
        this.team_name=team_name;
        this.team_manager=team_manager;
        this.team_mem_cnt=team_mem_cnt;
        this.team_pw=team_pw;
    }
}
