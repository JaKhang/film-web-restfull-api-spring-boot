package com.nlu.filmweb.payload.request;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorRequest {
    private String name;
    private Long countryId;
    private Date dateOfBirth;
    private String thumbnail;
    private Boolean gender;
}
