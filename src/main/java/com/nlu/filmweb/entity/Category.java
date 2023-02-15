package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "Categories")
@Getter@Setter
public class Category extends CommonEntity{

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Film> films = new LinkedList<>();

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
