package com.nlu.filmweb.payload.response;


import com.nlu.filmweb.payload.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmResponse {
    private Long id;
    private String title;
    private String poster;
    private List<CategoryResponse> categories;
    private String language;
}
