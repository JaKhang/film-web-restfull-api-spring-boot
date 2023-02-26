package com.nlu.filmweb.service;

import com.nlu.filmweb.payload.request.FilmRequest;
import com.nlu.filmweb.payload.response.FilmDetailsResponse;
import com.nlu.filmweb.payload.SourcePayload;
import com.nlu.filmweb.payload.response.FilmResponse;
import com.nlu.filmweb.service.CRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface FilmService extends CRUDService<FilmResponse, FilmDetailsResponse, FilmRequest> {
    Page<FilmResponse> getAll(Pageable pageable);

    SourcePayload addSourceFilm(Long filmId, SourcePayload sourcePayload);

    Page<FilmResponse> getAllByCategory(Long categoryId, Pageable pageable);

    Page<FilmResponse> searchFullText(String text, Pageable pageable);

    Page<FilmResponse>  getAllByPublishYear(Integer publishYear, PageRequest pageRequest);

    Page<FilmResponse>  getAllByCategoryAnhPublishYear(Long categoryId, Integer publishYear, PageRequest pageRequest);
}
