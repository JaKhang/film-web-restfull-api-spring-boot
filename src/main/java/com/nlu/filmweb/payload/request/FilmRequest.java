package com.nlu.filmweb.payload.request;



import lombok.*;

import java.util.List;

@Data
public class FilmRequest {
    private String title;
    private String subtitle;
    private String shortDescription;
    private String content;
    private Integer duration;
    private Long producerId;
    private Long directorId;
    private Long countryId;
    private Long languageId;
    private Long qualityId;
    private Long statusId;
    private String poster;
    private List<Long> categoryIds;
    private List<Long> actorIds;
    private Integer publishYear;

}
