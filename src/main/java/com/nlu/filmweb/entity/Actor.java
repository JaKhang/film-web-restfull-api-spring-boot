package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "Actors")
@Getter
@Setter
public class Actor extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36)
    private String name;

    @Column(length = 2048)
    private String thumbnail;

    @Column
    private Date dateOfBirth;

    @ManyToMany(mappedBy = "actors", fetch = FetchType.LAZY)
    private List<Film> films;

    @Column
    private Boolean gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

}
