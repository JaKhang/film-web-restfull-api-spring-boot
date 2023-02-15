package com.nlu.filmweb.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private Long id;
    private String title;
    private String poster;
    private List<CategoryDTO> categories;
    private String language;
}
