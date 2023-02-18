package com.nlu.filmweb.controller;

import com.nlu.filmweb.payload.request.FilmRequest;
import com.nlu.filmweb.payload.response.FilmResponse;
import com.nlu.filmweb.payload.response.FilmDetailsResponse;
import com.nlu.filmweb.payload.SourcePayload;
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
    public ResponseEntity<List<FilmResponse>> getAll(){
        return ResponseEntity.ok(filmService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FilmDetailsResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(filmService.getById(id));
    }
    @GetMapping("")
    public ResponseEntity<Page<FilmResponse>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer limit,
                                                     @RequestParam(defaultValue = "publishYear") String sortBy,
                                                     @RequestParam(defaultValue = "DESC") String direction){
        var sorter = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy, "createdBy");
        var pageRequest = PageRequest.of(page, limit, sorter);
        return ResponseEntity.ok(filmService.getAll(pageRequest));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Page<FilmResponse>> getAllByCategory(@PathVariable Long categoryId,
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
    public ResponseEntity<FilmDetailsResponse> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(filmService.deleteById(id));
    }


    /*------------------
          POST
    --------------------*/
    @PostMapping("")
    public ResponseEntity<FilmDetailsResponse> insert(@RequestBody FilmRequest filmRequest){
        System.out.println(filmRequest);
        return ResponseEntity.ok(filmService.insert(filmRequest));
    }

    @PostMapping("/{id}/film-sources")
    public ResponseEntity<SourcePayload> addSourceFilmById(@PathVariable Long id, @RequestBody SourcePayload sourcePayload){
        return ResponseEntity.ok(filmService.addSourceFilm(id, sourcePayload));
    }

    @PostMapping("/all")
    public ResponseEntity<List<FilmDetailsResponse>> insertAll(@RequestBody List<FilmRequest> filmRequests){
        var filmDetailDTOs = new ArrayList<FilmDetailsResponse>();
        for (var filmDetailDTO: filmRequests) {
            filmDetailDTOs.add(filmService.insert(filmDetailDTO));
        }
        return ResponseEntity.ok(filmDetailDTOs);
    }

    /*------------------
          PUT
    --------------------*/
    @PutMapping("/{id}")
    public ResponseEntity<FilmDetailsResponse> update(@RequestBody FilmRequest filmRequest, @PathVariable Long id){
        return ResponseEntity.ok(filmService.update(id, filmRequest));
    }



}
