package me.april.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String user_id;
    private String user_pw;
    private String user_name;

    @OneToMany(mappedBy = "user")
    private List <User_Team> teams = new ArrayList<>();

    @OneToMany(mappedBy= "user")
    private List <Post_Problem> problems=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List <Post_Code> user_codes = new ArrayList<>();
}
