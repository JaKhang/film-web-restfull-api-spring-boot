package com.nlu.filmweb.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "Languages")
@Entity
@Getter
@Setter
public class Language extends CommonEntity{
    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private List<Film> films;
}
