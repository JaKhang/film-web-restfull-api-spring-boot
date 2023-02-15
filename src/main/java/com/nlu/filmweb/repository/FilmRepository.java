package com.nlu.filmweb.repository;

import com.nlu.filmweb.entity.Category;
import com.nlu.filmweb.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Page<Film> findFilmsByCategoriesId(Long categories_id, Pageable pageable);

    Page<Film> findFilmsByPublishYear(Integer publishYear, Pageable pageable);
}
