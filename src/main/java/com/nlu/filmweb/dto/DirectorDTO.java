package com.nlu.filmweb.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class DirectorDTO {
    private Long id;
    private String name;
    private Date dateOfBirth;
}
