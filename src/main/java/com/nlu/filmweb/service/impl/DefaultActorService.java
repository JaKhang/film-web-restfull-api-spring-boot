package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.dto.ActorCreationDTO;
import com.nlu.filmweb.dto.ActorDTO;
import com.nlu.filmweb.dto.ActorDetailDTO;
import com.nlu.filmweb.dto.CategoryDTO;
import com.nlu.filmweb.entity.Actor;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.repository.ActorRepository;
import com.nlu.filmweb.repository.CountryRepository;
import com.nlu.filmweb.service.ActorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

import static com.nlu.filmweb.utils.AppConstant.*;

@Service
public class DefaultActorService implements ActorService {
    private final ActorRepository actorRepository;

    private final CountryRepository countryRepository;

    private final ModelMapper mapper;

    @Autowired
    public DefaultActorService(ActorRepository actorRepository, CountryRepository countryRepository, ModelMapper mapper) {
        this.actorRepository = actorRepository;
        this.countryRepository = countryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ActorDTO> getAll() {
        var actors = actorRepository.findAll();
        Type listType = new TypeToken<List<ActorDTO>>() {}.getType();
        return mapper.map(actors, listType);
    }

    @Override
    public ActorDetailDTO deleteById(Long id) {
        var actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        actorRepository.delete(actor);
        return mapper.map(actor, ActorDetailDTO.class);
    }

    @Override
    public ActorDetailDTO insert(ActorCreationDTO actorCreationDTO) {
        var actor = mapper.map(actorCreationDTO, Actor.class);
        var country = countryRepository.findById(actorCreationDTO.getCountryId()).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, actorCreationDTO.getCountryId()));
        actor.setCountry(country);
        actor = actorRepository.save(actor);
        return mapper.map(actor, ActorDetailDTO.class);
    }

    @Override
    public ActorDetailDTO update(Long id, ActorCreationDTO actorCreationDTO) {
        if(!actorRepository.existsById(id))
            throw new ResourceNotFoundException(ACTOR, ID, id);
        var country = countryRepository.findById(actorCreationDTO.getCountryId()).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, actorCreationDTO.getCountryId()));
        var actor = mapper.map(actorCreationDTO, Actor.class);
        actor.setId(id);
        actor.setCountry(country);
        actor = actorRepository.save(actor);
        return mapper.map(actor, ActorDetailDTO.class);
    }

    @Override
    public ActorDetailDTO getById(Long id) {
        var actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        return mapper.map(actor, ActorDetailDTO.class);
    }
}
