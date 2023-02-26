package com.nlu.filmweb.controller;

import com.nlu.filmweb.service.FilmService;
import com.nlu.filmweb.payload.SourcePayload;
import com.nlu.filmweb.payload.request.FilmRequest;
import com.nlu.filmweb.payload.response.FilmDetailsResponse;
import com.nlu.filmweb.payload.response.FilmResponse;
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
        var sorter = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy, "createdDate");
        var pageRequest = PageRequest.of(page, limit, sorter);
        return ResponseEntity.ok(filmService.getAll(pageRequest));
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<Page<FilmResponse>> getAllByCategory(@PathVariable Long categoryId,
                                                               @RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer limit,
                                                               @RequestParam(defaultValue = "publishYear") String sortBy,
                                                               @RequestParam(defaultValue = "DESC") String direction) {
        var sorter = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy, "createdDate");
        var pageRequest = PageRequest.of(page, limit, sorter);
        return ResponseEntity.ok(filmService.getAllByCategory(categoryId, pageRequest));
    }

    @GetMapping("/query")
    public ResponseEntity<Page<FilmResponse>> searchFullText(@RequestParam String query,
                                                              @RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer limit,
                                                              @RequestParam(defaultValue = "publishYear") String sortBy,
                                                              @RequestParam(defaultValue = "DESC") String direction){
        var sorter = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), camelToSnake(sortBy));
        var pageRequest = PageRequest.of(page, limit, sorter);
        return ResponseEntity.ok(filmService.searchFullText(query, pageRequest));
    }
    @GetMapping("/filter")
    public ResponseEntity<Page<FilmResponse>> filter(        @RequestParam(defaultValue = "0") Integer publishYear,
                                                             @RequestParam(defaultValue = "0") Long categoryId,
                                                             @RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer limit,
                                                             @RequestParam(defaultValue = "publishYear") String sortBy,
                                                             @RequestParam(defaultValue = "DESC") String direction) {
        var sorter = Sort.by(Sort.Direction.valueOf(direction.toUpperCase()), sortBy);
        var pageRequest = PageRequest.of(page, limit, sorter);
        if(publishYear == 0 && categoryId == 0){
            return ResponseEntity.ok(filmService.getAll(pageRequest));
        }
        if(publishYear == 0){
            return ResponseEntity.ok(filmService.getAllByCategory(categoryId, pageRequest));
        }
        if(categoryId == 0){
            return ResponseEntity.ok(filmService.getAllByPublishYear(publishYear, pageRequest));
        }
        return ResponseEntity.ok(filmService.getAllByCategoryAnhPublishYear(categoryId, publishYear, pageRequest));
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

    private String camelToSnake(String str) {

        // Empty String
        StringBuilder result = new StringBuilder();

        // Append first character(in lower case)
        // to result string
        char c = str.charAt(0);
        result.append(Character.toLowerCase(c));

        // Traverse the string from
        // ist index to last index
        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            }

            // If the character is lower case then
            // add such character into result string
            else {
                result.append(ch);
            }
        }
        return result.toString();

    }

}
