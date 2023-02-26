package com.nlu.filmweb.payload.response;
import com.nlu.filmweb.payload.SourcePayload;
import com.nlu.filmweb.payload.response.ActorResponse;
import com.nlu.filmweb.payload.response.CategoryResponse;
import com.nlu.filmweb.payload.response.CommonResponse;
import com.nlu.filmweb.payload.response.DirectorResponse;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
@Data
public class FilmDetailsResponse {
    private Long id;
    private String title;
    private String subtitle;
    private String shortDescription;
    private String content;
    private String poster;
    private String trailer;
    private Integer duration;
    private Integer publishYear;
    private CommonResponse producer;
    private String language;
    private String status;
    private CommonResponse country;
    private List<ActorResponse> actors;
    private List<CategoryResponse> categories;
    private String quality;
    private DirectorResponse director;
    private List<SourcePayload> sourceFilms;
    protected Timestamp lastModifiedDate;
    protected Timestamp createdDate;
}
