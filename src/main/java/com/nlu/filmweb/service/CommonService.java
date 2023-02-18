package com.nlu.filmweb.service;

import com.nlu.filmweb.payload.request.CommonRequest;
import com.nlu.filmweb.payload.response.CommonResponse;
import com.nlu.filmweb.exception.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Type;
import java.util.List;

import static com.nlu.filmweb.utils.AppConstant.CATEGORY;
import static com.nlu.filmweb.utils.AppConstant.ID;

public abstract class CommonService<T> implements CRUDService<CommonResponse, CommonResponse, CommonRequest> {
    protected JpaRepository<T, Long> commonRepository;
    @Autowired
    protected ModelMapper mapper;

    @Override
    public List<CommonResponse> getAll() {
        Type listType = new TypeToken<List<CommonResponse>>(){}.getType();
        return mapper.map(commonRepository.findAll(), listType);
    }

    @Override
    public CommonResponse deleteById(Long id) {
        var entity = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource", ID, id));
        commonRepository.deleteById(id);
        return mapper.map(entity, CommonResponse.class);
    }

    public CommonResponse getById(Long id) {
        var commonEntity = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ID, CATEGORY, id));
        return mapper.map(commonEntity, CommonResponse.class);
    }

    public void setCommonRepository(JpaRepository<T, Long>  commonRepository) {
        this.commonRepository = commonRepository;
    }
}
