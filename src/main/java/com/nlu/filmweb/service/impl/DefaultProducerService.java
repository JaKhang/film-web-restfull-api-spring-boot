package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.payload.request.CommonRequest;
import com.nlu.filmweb.payload.response.CommonResponse;
import com.nlu.filmweb.entity.Producer;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import static com.nlu.filmweb.utils.AppConstant.COUNTRY;
import static com.nlu.filmweb.utils.AppConstant.ID;

@Service
public class DefaultProducerService extends CommonService<Producer> {
    @Autowired
    public void setCommonRepository(JpaRepository<Producer, Long> commonRepository) {
        super.setCommonRepository(commonRepository);
    }
    @Override
    public CommonResponse update(Long id, CommonRequest commonRequest) {
        var quality = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        quality.setCode(commonRequest.getCode());
        quality.setName(commonRequest.getName());
        return mapper.map(quality, CommonResponse.class);
    }

    @Override
    public CommonResponse insert(CommonRequest qualityDTO) {
        var quality = mapper.map(qualityDTO, Producer.class);
        return mapper.map(commonRepository.save(quality), CommonResponse.class);
    }

}
