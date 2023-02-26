package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
@MappedSuperclass
@Getter
@Setter
public abstract class Auditable {

    @CreatedDate
    protected Timestamp createdDate;

    @LastModifiedDate
    protected Timestamp lastModifiedDate;

    @LastModifiedBy
    protected String createdBy;

    @LastModifiedDate
    protected String lastModifiedBy;


}
