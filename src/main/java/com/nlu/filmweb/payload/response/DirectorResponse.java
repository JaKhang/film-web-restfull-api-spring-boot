package com.nlu.filmweb.payload.response;

import lombok.Data;

import java.util.Date;

@Data
public class DirectorResponse {
    private Long id;
    private String name;
    private Date dateOfBirth;
}
