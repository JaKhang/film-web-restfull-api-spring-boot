package com.nlu.filmweb;

import com.nlu.filmweb.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class FilmsApiApplicationTests {

    @Autowired
    private FilmRepository filmRepository;


    @Test
    void contextLoads() {

    }

    @Test
    void searchByTile(){
        Page<Long> films = filmRepository.searchFilmIdFullText("Cho", Pageable.ofSize(10));
        films.forEach(System.out::println);

    }

}
