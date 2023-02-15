package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Directors")
@Getter@Setter
public class Director{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32)
    private String name;

    @Column
    private Date dateOfBirth;

    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    private List<Film> films;

}
