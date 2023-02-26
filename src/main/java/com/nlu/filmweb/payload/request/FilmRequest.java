package com.nlu.filmweb.payload.request;



import com.nlu.filmweb.payload.SourcePayload;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Data
public class FilmRequest {
    private String title;
    private String subtitle;
    private String shortDescription;
    private String content;
    private Integer duration;
    private String trailer;
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
    private List<SourcePayload> sourceFilms = new LinkedList<>();

}
