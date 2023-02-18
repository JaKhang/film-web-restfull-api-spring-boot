package com.nlu.filmweb.payload.request;

import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryRequest {
    private String code;
    public String name;
}
