package com.nlu.filmweb.controller;

import com.nlu.filmweb.dto.FilmCreationDTO;
import com.nlu.filmweb.dto.FilmDTO;
import com.nlu.filmweb.dto.FilmDetailDTO;
import com.nlu.filmweb.dto.SourceDTO;
import com.nlu.filmweb.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    /*------------------
          GET
    --------------------*/
    @GetMapping("/all")
    public ResponseEntity<List<FilmDTO>> getAll(){
        return ResponseEntity.ok(filmService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FilmDetailDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(filmService.getById(id));
    }
    @GetMapping("")
    public ResponseEntity<Page<FilmDTO>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "10") Integer limit,
                                                @RequestParam(defaultValue = "publishYear") String sortBy,
                                                @RequestParam(defaultValue = "DESC") String direction){
        var sorter = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy, "createdBy");
        var pageRequest = PageRequest.of(page, limit, sorter);
        return ResponseEntity.ok(filmService.getAll(pageRequest));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Page<FilmDTO>> getAllByCategory(@PathVariable Long categoryId,
                                                          @RequestParam(defaultValue = "0") Integer page,
                                                          @RequestParam(defaultValue = "10") Integer limit,
                                                          @RequestParam(defaultValue = "publishYear") String sortBy,
                                                          @RequestParam(defaultValue = "DESC") String direction) {
        var sorter = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy, "createdBy");
        var pageRequest = PageRequest.of(page, limit, sorter);
        return ResponseEntity.ok(filmService.getAllByCategory(categoryId, pageRequest));
    }



    /*------------------
          DELETE
    --------------------*/
    @DeleteMapping("/{id}")
    public ResponseEntity<FilmDetailDTO> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(filmService.deleteById(id));
    }


    /*------------------
          POST
    --------------------*/
    @PostMapping("")
    public ResponseEntity<FilmDetailDTO> insert(@RequestBody FilmCreationDTO filmCreationDTO){
        System.out.println(filmCreationDTO);
        return ResponseEntity.ok(filmService.insert(filmCreationDTO));
    }

    @PostMapping("/{id}/film-sources")
    public ResponseEntity<SourceDTO> addSourceFilmById(@PathVariable Long id, @RequestBody SourceDTO sourceDTO){
        return ResponseEntity.ok(filmService.addSourceFilm(id, sourceDTO));
    }

    @PostMapping("/all")
    public ResponseEntity<List<FilmDetailDTO>> insertAll(@RequestBody List<FilmCreationDTO> filmCreationDTOS){
        var filmDetailDTOs = new ArrayList<FilmDetailDTO>();
        for (var filmDetailDTO: filmCreationDTOS) {
            filmDetailDTOs.add(filmService.insert(filmDetailDTO));
        }
        return ResponseEntity.ok(filmDetailDTOs);
    }

    /*------------------
          PUT
    --------------------*/
    @PutMapping("/{id}")
    public ResponseEntity<FilmDetailDTO> update(@RequestBody FilmCreationDTO filmCreationDTO, @PathVariable Long id){
        return ResponseEntity.ok(filmService.update(id, filmCreationDTO));
    }



}
