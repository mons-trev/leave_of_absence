package me.april.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post_Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String language; // 코드의 언어
    private boolean success; // 성공 여부
    private int like; // 좋아요 수
    private String algorithm; // 알고리즘 종류

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_problem_id")
    private Post_Problem post_problem;
}
