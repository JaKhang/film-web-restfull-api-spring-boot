package com.nlu.filmweb.controller;

import com.nlu.filmweb.entity.Category;
import com.nlu.filmweb.entity.Film;
import com.nlu.filmweb.entity.User;
import com.nlu.filmweb.repository.CategoryRepository;
import com.nlu.filmweb.repository.FilmRepository;
import com.nlu.filmweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private FilmRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Set<Category>> getUserByID(@PathVariable Long id){
        System.out.println(categoryRepository.getReferenceById(id).getFilms());
        return ResponseEntity.ok().build();
    }
}
