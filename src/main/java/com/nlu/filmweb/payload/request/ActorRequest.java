package com.nlu.filmweb.payload.request;

import lombok.*;

import java.util.Date;


@Data
public class ActorRequest {
    private String name;
    private Long countryId;
    private Date dateOfBirth;
    private String thumbnail;
    private Boolean gender;
}
