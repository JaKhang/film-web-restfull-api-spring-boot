package com.nlu.filmweb.dto;


import lombok.Data;

import java.util.Date;
@Data
public class ActorDetailDTO {
    private Long id;
    private String name;
    private String nationality;
    private Date dateOfBirth;
    private String thumbnail;
    private Boolean gender;
}
