package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.dto.CommonCreationDTO;
import com.nlu.filmweb.dto.CommonDTO;
import com.nlu.filmweb.entity.Quality;
import com.nlu.filmweb.entity.Status;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import static com.nlu.filmweb.utils.AppConstant.COUNTRY;
import static com.nlu.filmweb.utils.AppConstant.ID;

@Service
public class DefaultStatusService extends CommonService<Status> {
    @Autowired
    public void setCommonRepository(JpaRepository<Status, Long> commonRepository) {
        super.setCommonRepository(commonRepository);
    }
    @Override
    public CommonDTO update(Long id, CommonCreationDTO commonCreationDTO) {
        var status = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        status.setCode(commonCreationDTO.getCode());
        status.setName(commonCreationDTO.getName());
        return mapper.map(status, CommonDTO.class);
    }

    @Override
    public CommonDTO insert(CommonCreationDTO commonCreationDTO) {
        var status = mapper.map(commonCreationDTO, Status.class);
        return mapper.map(commonRepository.save(status), CommonDTO.class);
    }
}
