package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(length = 32)
    protected String name;
    @Column(length = 32)
    protected String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonEntity that)) return false;

        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
