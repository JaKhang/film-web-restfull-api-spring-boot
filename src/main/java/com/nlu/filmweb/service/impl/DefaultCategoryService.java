package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.dto.CategoryCreationDTO;
import com.nlu.filmweb.dto.CategoryDTO;
import com.nlu.filmweb.entity.Category;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.repository.CategoryRepository;
import com.nlu.filmweb.service.CategoryService;
import com.nlu.filmweb.utils.AppConstant;
import com.nlu.filmweb.utils.AppConstant.*;
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
    public List<CategoryDTO> getAll() {
        var categories = categoryRepository.findAll();
        Type listType = new TypeToken<List<CategoryDTO>>(){}.getType();
        return mapper.map(categories, listType);
    }
    @Override
    public CategoryDTO insert(CategoryCreationDTO categoryCreationDTO) {
        var category = mapper.map(categoryCreationDTO, Category.class);
        category = categoryRepository.save(category);
        return mapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long id, CategoryCreationDTO categoryCreationDTO) {
        return null;
    }

    @Override
    public CategoryDTO deleteById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(CATEGORY, ID, id));
        categoryRepository.delete(category);
        return mapper.map(category, CategoryDTO.class);
    }
    @Override
    public CategoryDTO getById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ID, CATEGORY, id));
        return mapper.map(category, CategoryDTO.class);
    }
}
