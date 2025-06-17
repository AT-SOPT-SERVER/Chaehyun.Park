package org.sopt.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> postEntities = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> commentEntities = new ArrayList<>();

    public User() {

    }

    public User(String name){
        this.name = name;
    }

    public Long getId(){return id;}

    public String getName(){return name;}

    public List<Post> getPostEntities() {
        return postEntities;
    }
}
