package com.nlu.filmweb.service;

import com.nlu.filmweb.payload.request.FilmRequest;
import com.nlu.filmweb.payload.response.FilmResponse;
import com.nlu.filmweb.payload.response.FilmDetailsResponse;
import com.nlu.filmweb.payload.SourcePayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService extends CRUDService<FilmResponse, FilmDetailsResponse, FilmRequest>{
    Page<FilmResponse> getAll(Pageable pageable);

    SourcePayload addSourceFilm(Long filmId, SourcePayload sourcePayload);

    Page<FilmResponse> getAllByCategory(Long categoryId, Pageable pageable);
}
