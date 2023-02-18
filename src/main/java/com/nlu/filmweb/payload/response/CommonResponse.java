package com.nlu.filmweb.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private Long id;
    private String name;
    private String code;
}
