package com.nlu.filmweb.payload.response;


import lombok.Data;

import java.util.Date;
@Data
public class ActorDetailsResponse {
    private Long id;
    private String name;
    private String nationality;
    private Date dateOfBirth;
    private String thumbnail;
    private Boolean gender;
}
