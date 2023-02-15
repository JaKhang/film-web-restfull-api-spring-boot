package com.nlu.filmweb.controller;

import com.nlu.filmweb.dto.CategoryCreationDTO;
import com.nlu.filmweb.dto.CategoryDTO;
import com.nlu.filmweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        var categoryDTO = categoryService.getById(id);
        return ResponseEntity.status(200).body(categoryDTO);
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryCreationDTO category) {
        CategoryDTO categoryDTO = categoryService.insert(category);
        return ResponseEntity.status(200).body(categoryDTO);
    }

    @PostMapping("/all")
    public ResponseEntity<List<CategoryDTO>> insertAll(@RequestBody List<CategoryCreationDTO> categories) {
        var categoryDTOs = new ArrayList<CategoryDTO>();
        for (var category:categories) {
            categoryDTOs.add(categoryService.insert(category));
        }
        return ResponseEntity.status(200).body(categoryDTOs);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> deletedCategory(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.deleteById(id);
        return ResponseEntity.status(200).body(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryCreationDTO category, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.update(id, category));
    }

}
