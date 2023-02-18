package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.payload.request.CategoryRequest;
import com.nlu.filmweb.payload.response.CategoryResponse;
import com.nlu.filmweb.entity.Category;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.repository.CategoryRepository;
import com.nlu.filmweb.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.lang.reflect.Type;
import java.util.List;

import static com.nlu.filmweb.utils.AppConstant.CATEGORY;
import static com.nlu.filmweb.utils.AppConstant.ID;

@Service
public class DefaultCategoryService implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public List<CategoryResponse> getAll() {
        var categories = categoryRepository.findAll();
        Type listType = new TypeToken<List<CategoryResponse>>(){}.getType();
        return mapper.map(categories, listType);
    }
    @Override
    public CategoryResponse insert(CategoryRequest categoryRequest) {
        var category = mapper.map(categoryRequest, Category.class);
        category = categoryRepository.save(category);
        return mapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public CategoryResponse deleteById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, id));
        categoryRepository.delete(category);
        return mapper.map(category, CategoryResponse.class);
    }
    @Override
    public CategoryResponse getById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ID, CATEGORY, id));
        return mapper.map(category, CategoryResponse.class);
    }
}
