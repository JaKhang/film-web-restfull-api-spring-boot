package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Producers")
@Getter
@Setter
public class Producer extends CommonEntity{

    @OneToMany(mappedBy = "producer", fetch = FetchType.LAZY)
    private List<Film> films;

}
