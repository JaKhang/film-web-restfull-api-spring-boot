package com.nlu.filmweb.repository;

import com.nlu.filmweb.entity.Category;
import com.nlu.filmweb.entity.Film;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Page<Film> findFilmsByCategoriesId(Long categories_id, Pageable pageable);

    Page<Film> findFilmsByPublishYear(Integer publishYear, Pageable pageable);

    @Query(value = "SELECT films.id FROM films WHERE MATCH(title, subtitle) AGAINST (?1 IN BOOLEAN MODE) OR title like %?1%", nativeQuery = true, countQuery = "SELECT count(*) FROM films WHERE MATCH(title, subtitle) AGAINST (?1) like %?1%")
    Page<Long> searchFilmIdFullText(String text, Pageable pageable);

    Page<Film> findFilmsByCategoriesIdAndPublishYear(Long categoryId, Integer publishYear, PageRequest pageRequest);
}
