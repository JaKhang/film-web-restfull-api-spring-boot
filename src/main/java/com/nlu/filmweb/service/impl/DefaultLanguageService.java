package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.payload.request.CommonRequest;
import com.nlu.filmweb.payload.response.CommonResponse;
import com.nlu.filmweb.entity.Language;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import static com.nlu.filmweb.utils.AppConstant.COUNTRY;
import static com.nlu.filmweb.utils.AppConstant.ID;

@Service
public class DefaultLanguageService extends CommonService<Language> {

    @Autowired
    public void setCommonRepository(JpaRepository<Language, Long> commonRepository) {
        super.setCommonRepository(commonRepository);
    }

    @Override
    public CommonResponse update(Long id, CommonRequest commonRequest) {
        var status = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        status.setCode(commonRequest.getCode());
        status.setName(commonRequest.getName());
        return mapper.map(status, CommonResponse.class);
    }

    @Override
    public CommonResponse insert(CommonRequest commonRequest) {
        var status = mapper.map(commonRequest, Language.class);
        return mapper.map(commonRepository.save(status), CommonResponse.class);
    }
}
