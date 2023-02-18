package com.nlu.filmweb.config;

import com.nlu.filmweb.entity.Actor;
import com.nlu.filmweb.entity.Film;
import com.nlu.filmweb.payload.response.ActorDetailsResponse;
import com.nlu.filmweb.payload.response.FilmDetailsResponse;
import com.nlu.filmweb.payload.response.FilmResponse;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class MappingConfig {
    @Bean
    ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(new ActorDetailConfig());
        modelMapper.addMappings(new FilmDetailConfig(modelMapper));
        modelMapper.addMappings(new FilmConfig());
        return modelMapper;
    }
}

class ActorDetailConfig extends PropertyMap<Actor, ActorDetailsResponse>{
    @Override
    protected void configure() {
        map().setNationality(source.getCountry().getName());
    }
}

class FilmConfig extends PropertyMap<Film, FilmResponse>{
    @Override
    protected void configure() {
        map().setLanguage(source.getLanguage().getName());
    }
}

class FilmDetailConfig extends PropertyMap<Film, FilmDetailsResponse>{
    private ModelMapper mapper;

    FilmDetailConfig(ModelMapper mapper) {
        super();
        this.mapper = mapper;
    }

    @Override
    protected void configure() {
        map().setLanguage(source.getLanguage().getName());
        map().setQuality(source.getQuality().getName());
        map().setStatus(source.getStatus().getName());
    }
}


