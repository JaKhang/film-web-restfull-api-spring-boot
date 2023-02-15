package com.nlu.filmweb.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorCreationDTO {
    private String name;
    private Long countryId;
    private Date dateOfBirth;
    private String thumbnail;
    private Boolean gender;
}
