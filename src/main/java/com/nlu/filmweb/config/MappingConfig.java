package com.nlu.filmweb.config;

import com.nlu.filmweb.dto.*;
import com.nlu.filmweb.entity.Actor;
import com.nlu.filmweb.entity.Film;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Type;
import java.util.List;

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

class ActorDetailConfig extends PropertyMap<Actor, ActorDetailDTO>{
    @Override
    protected void configure() {
        map().setNationality(source.getCountry().getName());
    }
}

class FilmConfig extends PropertyMap<Film, FilmDTO>{
    @Override
    protected void configure() {
        map().setLanguage(source.getLanguage().getName());
    }
}

class FilmDetailConfig extends PropertyMap<Film, FilmDetailDTO>{
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


