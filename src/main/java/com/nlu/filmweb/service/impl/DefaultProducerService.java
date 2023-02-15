package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.dto.CommonCreationDTO;
import com.nlu.filmweb.dto.CommonDTO;
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
    public CommonDTO update(Long id, CommonCreationDTO commonCreationDTO) {
        var quality = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        quality.setCode(commonCreationDTO.getCode());
        quality.setName(commonCreationDTO.getName());
        return mapper.map(quality, CommonDTO.class);
    }

    @Override
    public CommonDTO insert(CommonCreationDTO qualityDTO) {
        var quality = mapper.map(qualityDTO, Producer.class);
        return mapper.map(commonRepository.save(quality), CommonDTO.class);
    }

}
