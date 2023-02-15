package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Qualities")
@Getter
@Setter
public class Quality extends CommonEntity{

    @OneToMany(mappedBy = "quality", fetch = FetchType.LAZY)
    private List<Film> films;
}
