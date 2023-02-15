package com.nlu.filmweb.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
@MappedSuperclass
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
