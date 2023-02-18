package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.payload.request.CommonRequest;
import com.nlu.filmweb.payload.response.CommonResponse;
import com.nlu.filmweb.entity.Country;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.service.CommonService;
import com.nlu.filmweb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import static com.nlu.filmweb.utils.AppConstant.COUNTRY;
import static com.nlu.filmweb.utils.AppConstant.ID;

@Service
public class DefaultCountryService extends CommonService<Country> implements CountryService {

    @Autowired
    public void setCommonRepository(JpaRepository<Country, Long> commonRepository) {
        super.setCommonRepository(commonRepository);
    }

    @Override
    public CommonResponse update(Long id, CommonRequest commonRequest) {
        var country = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        country.setCode(commonRequest.getCode());
        country.setName(commonRequest.getName());
        return mapper.map(country, CommonResponse.class);
    }

    @Override
    public CommonResponse insert(CommonRequest countryDTO) {
        var country = mapper.map(countryDTO, Country.class);
        return mapper.map(commonRepository.save(country), CommonResponse.class);
    }
}
