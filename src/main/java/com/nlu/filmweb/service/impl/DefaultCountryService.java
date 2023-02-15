package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.dto.CommonCreationDTO;
import com.nlu.filmweb.dto.CommonDTO;
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
    public CommonDTO update(Long id, CommonCreationDTO commonCreationDTO) {
        var country = commonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        country.setCode(commonCreationDTO.getCode());
        country.setName(commonCreationDTO.getName());
        return mapper.map(country, CommonDTO.class);
    }

    @Override
    public CommonDTO insert(CommonCreationDTO countryDTO) {
        var country = mapper.map(countryDTO, Country.class);
        return mapper.map(commonRepository.save(country), CommonDTO.class);
    }
}
