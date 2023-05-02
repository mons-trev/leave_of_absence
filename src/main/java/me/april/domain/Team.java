package me.april.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String team_name;

    private int team_mem_count;

    @ManyToOne
    @JoinColumn(name = "user_id") // id로 join
    private User team_manager; // join 하는 것은 user

    @OneToMany(mappedBy = "team")
    private List<User_Team> users = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Post_Problem> problems = new ArrayList<>();
}
