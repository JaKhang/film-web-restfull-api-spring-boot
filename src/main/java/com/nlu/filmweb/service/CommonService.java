package com.nlu.filmweb.service;

import com.nlu.filmweb.dto.CommonCreationDTO;
import com.nlu.filmweb.dto.CommonDTO;
import com.nlu.filmweb.exception.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Type;
import java.util.List;

import static com.nlu.filmweb.utils.AppConstant.CATEGORY;
import static com.nlu.filmweb.utils.AppConstant.ID;

public abstract class CommonService<T> implements CRUDService<CommonDTO, CommonDTO, CommonCreationDTO> {
    protected JpaRepository<T, Long> commonRepository;
    @Autowired
    protected ModelMapper mapper;

    @Override
    public List<CommonDTO> getAll() {
        Type listType = new TypeToken<List<CommonDTO>>(){}.getType();
        return mapper.map(commonRepository.findAll(), listType);
    }

    @Override
    public CommonDTO deleteById(Long id) {
        var entity = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource", ID, id));
        commonRepository.deleteById(id);
        return mapper.map(entity, CommonDTO.class);
    }

    public CommonDTO getById(Long id) {
        var commonEntity = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ID, CATEGORY, id));
        return mapper.map(commonEntity, CommonDTO.class);
    }

    public void setCommonRepository(JpaRepository<T, Long>  commonRepository) {
        this.commonRepository = commonRepository;
    }
}
