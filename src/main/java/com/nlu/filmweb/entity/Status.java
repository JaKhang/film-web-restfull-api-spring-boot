package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "Statuses")
@Getter
@Setter
public class Status extends CommonEntity{
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<Film> films;
}
