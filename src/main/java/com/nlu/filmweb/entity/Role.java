package com.nlu.filmweb.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Roles")
public class Role extends CommonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> users;

}
