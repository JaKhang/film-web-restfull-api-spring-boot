package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country extends CommonEntity{

    @OneToMany(mappedBy = "country")
    private List<Film> films;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Actor> actors;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
