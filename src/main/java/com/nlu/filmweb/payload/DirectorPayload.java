package com.nlu.filmweb.payload;

import lombok.Data;

import java.util.Date;

@Data
public class DirectorPayload {
    private Long id;
    private String name;
    private Date dateOfBirth;
}
