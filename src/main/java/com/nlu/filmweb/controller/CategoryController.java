package com.nlu.filmweb.controller;

import com.nlu.filmweb.payload.request.CategoryRequest;
import com.nlu.filmweb.payload.response.CategoryResponse;
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
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        var categoryDTO = categoryService.getById(id);
        return ResponseEntity.status(200).body(categoryDTO);
    }

    @PostMapping("")
    public ResponseEntity<CategoryResponse> insert(@RequestBody CategoryRequest category) {
        CategoryResponse categoryResponse = categoryService.insert(category);
        return ResponseEntity.status(200).body(categoryResponse);
    }

    @PostMapping("/all")
    public ResponseEntity<List<CategoryResponse>> insertAll(@RequestBody List<CategoryRequest> categories) {
        var categoryDTOs = new ArrayList<CategoryResponse>();
        for (var category:categories) {
            categoryDTOs.add(categoryService.insert(category));
        }
        return ResponseEntity.status(200).body(categoryDTOs);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deletedCategory(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.deleteById(id);
        return ResponseEntity.status(200).body(categoryResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest category, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.update(id, category));
    }

}
