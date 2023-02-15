package com.nlu.filmweb.service;

import com.nlu.filmweb.dto.FilmCreationDTO;
import com.nlu.filmweb.dto.FilmDTO;
import com.nlu.filmweb.dto.FilmDetailDTO;
import com.nlu.filmweb.dto.SourceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService extends CRUDService<FilmDTO, FilmDetailDTO, FilmCreationDTO>{
    Page<FilmDTO> getAll(Pageable pageable);

    SourceDTO addSourceFilm(Long filmId, SourceDTO sourceDTO);

    Page<FilmDTO> getAllByCategory(Long categoryId, Pageable pageable);
}
