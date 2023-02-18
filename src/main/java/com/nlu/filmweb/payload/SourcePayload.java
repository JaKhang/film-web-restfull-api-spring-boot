package com.nlu.filmweb.payload;


import lombok.Data;

@Data
public class SourcePayload {
    private Long id;
    private Integer episode;
    private String url;
}
