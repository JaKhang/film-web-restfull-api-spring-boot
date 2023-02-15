package com.nlu.filmweb.dto;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
@Data
public class FilmDetailDTO {
    private Long id;
    private String title;
    private String subtitle;
    private String shortDescription;
    private String content;
    private String poster;
    private String trailer;
    private Integer duration;
    private Integer publishYear;
    private CommonDTO producer;
    private String language;
    private String status;
    private CommonDTO country;
    private List<ActorDTO> actors;
    private List<CategoryDTO> categories;
    private String quality;
    private DirectorDTO director;
    private List<SourceDTO> sourceFilms;
    protected Timestamp lastModifiedDate;
    protected Timestamp createdDate;
}
