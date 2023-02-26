package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.entity.Country;
import com.nlu.filmweb.payload.request.ActorRequest;
import com.nlu.filmweb.payload.response.ActorResponse;
import com.nlu.filmweb.payload.response.ActorDetailsResponse;
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
    public List<ActorResponse> getAll() {
        var actors = actorRepository.findAll();
        Type listType = new TypeToken<List<ActorResponse>>() {}.getType();
        return mapper.map(actors, listType);
    }

    @Override
    public ActorDetailsResponse deleteById(Long id) {
        var actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        actorRepository.delete(actor);
        return mapper.map(actor, ActorDetailsResponse.class);
    }

    @Override
    public ActorDetailsResponse insert(ActorRequest actorRequest) {
        var actor = mapper.map(actorRequest, Actor.class);
        Country country = null;
        if(actorRequest.getCountryId() != null){
            country = countryRepository.findById(actorRequest.getCountryId()).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, actorRequest.getCountryId()));
        }
        actor.setCountry(country);
        actor = actorRepository.save(actor);
        return mapper.map(actor, ActorDetailsResponse.class);
    }

    @Override
    public ActorDetailsResponse update(Long id, ActorRequest actorRequest) {
        if(!actorRepository.existsById(id))
            throw new ResourceNotFoundException(ACTOR, ID, id);
        var country = countryRepository.findById(actorRequest.getCountryId()).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, actorRequest.getCountryId()));
        var actor = mapper.map(actorRequest, Actor.class);
        actor.setId(id);
        actor.setCountry(country);
        actor = actorRepository.save(actor);
        return mapper.map(actor, ActorDetailsResponse.class);
    }

    @Override
    public ActorDetailsResponse getById(Long id) {
        var actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COUNTRY, ID, id));
        return mapper.map(actor, ActorDetailsResponse.class);
    }
}
