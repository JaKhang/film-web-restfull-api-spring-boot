package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.dto.FilmCreationDTO;
import com.nlu.filmweb.dto.FilmDTO;
import com.nlu.filmweb.dto.FilmDetailDTO;
import com.nlu.filmweb.dto.SourceDTO;
import com.nlu.filmweb.entity.Film;
import com.nlu.filmweb.entity.SourceFilm;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.repository.*;
import com.nlu.filmweb.service.FilmService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

import static com.nlu.filmweb.utils.AppConstant.*;

@Service
public class DefaultFilmService implements FilmService {
    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;
    private final ProducerRepository producerRepository;
    private final CountryRepository countryRepository;
    private final StatusRepository statusRepository;
    private final ActorRepository actorRepository;
    private final QualityRepository qualityRepository;
    private final DirectorRepository directorRepository;
    private final SourceFilmRepository sourceFilmRepository;

    private final LanguageRepository languageRepository;

    private final ModelMapper mapper;


    @Autowired
    public DefaultFilmService(FilmRepository filmRepository, CategoryRepository categoryRepository, ProducerRepository producerRepository, CountryRepository countryRepository, StatusRepository statusRepository, ActorRepository actorRepository, QualityRepository qualityRepository, DirectorRepository directorRepository, SourceFilmRepository sourceFilmRepository, LanguageRepository languageRepository, ModelMapper mapper) {
        this.filmRepository = filmRepository;
        this.categoryRepository = categoryRepository;
        this.producerRepository = producerRepository;
        this.countryRepository = countryRepository;
        this.statusRepository = statusRepository;
        this.actorRepository = actorRepository;
        this.qualityRepository = qualityRepository;
        this.directorRepository = directorRepository;
        this.sourceFilmRepository = sourceFilmRepository;
        this.languageRepository = languageRepository;
        this.mapper = mapper;
    }


    @Override
    public List<FilmDTO> getAll() {
        Type listType = new TypeToken<List<FilmDTO>>() {
        }.getType();
        return mapper.map(filmRepository.findAll(), listType);
    }

    @Override
    public FilmDetailDTO deleteById(Long id) {
        var film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FILM, ID, id));
        filmRepository.deleteById(id);
        return mapper.map(film, FilmDetailDTO.class);
    }

    @Override
    public FilmDetailDTO insert(FilmCreationDTO filmCreationDTO) {
        Film film = mapFilm(filmCreationDTO);
        film = filmRepository.save(film);
        return mapper.map(film, FilmDetailDTO.class);
    }

    @Override
    public FilmDetailDTO update(Long id, FilmCreationDTO filmCreationDTO) {
        if(!filmRepository.existsById(id))
            throw new ResourceNotFoundException(FILM, ID, id);
        Film film = mapFilm(filmCreationDTO);
        film.setId(id);
        film = filmRepository.save(film);
        return mapper.map(film, FilmDetailDTO.class);

    }
    private Film mapFilm(FilmCreationDTO filmCreationDTO){
        var country = countryRepository.findById(filmCreationDTO.getCountryId()).orElse(null);
        var categories = categoryRepository.findAllById(filmCreationDTO.getCategoryIds());
        var actors = actorRepository.findAllById(filmCreationDTO.getActorIds());
        var quality = qualityRepository.findById(filmCreationDTO.getQualityId()).orElse(null);
        var language = languageRepository.findById(filmCreationDTO.getLanguageId()).orElse(null);
        var status = statusRepository.findById(filmCreationDTO.getStatusId()).orElse(null);
        var director = directorRepository.findById(filmCreationDTO.getDirectorId()).orElse(null);
        var producer = producerRepository.findById(filmCreationDTO.getProducerId()).orElse(null);
        var film = mapper.map(filmCreationDTO, Film.class);
        film.setActors(actors);
        film.setCategories(categories);
        film.setCountry(country);
        film.setLanguage(language);
        film.setDirector(director);
        film.setQuality(quality);
        film.setProducer(producer);
        film.setStatus(status);
        return film;
    }

    @Override
    public FilmDetailDTO getById(Long id) {
        var film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FILM, ID, id));
        System.out.println(film);
        return mapper.map(film, FilmDetailDTO.class);
    }

    @Override
    public Page<FilmDTO> getAll(Pageable pageable) {
        return filmRepository.findAll(pageable).map(film -> mapper.map(film, FilmDTO.class));
    }

    @Override
    public SourceDTO addSourceFilm(Long filmId, SourceDTO sourceDTO) {
        var film = filmRepository.findById(filmId).orElseThrow(() -> new ResourceNotFoundException(FILM, ID, filmId));
        var sourceFilm = mapper.map(sourceDTO, SourceFilm.class);
        sourceFilm.setFilms(film);
        return mapper.map(sourceFilmRepository.save(sourceFilm), SourceDTO.class);
    }

    @Override
    public Page<FilmDTO> getAllByCategory(Long categoryId, Pageable pageable) {
        if(categoryRepository.existsById(categoryId))
            throw new  ResourceNotFoundException(CATEGORY, ID, categoryId);
        return filmRepository.findFilmsByCategoriesId(categoryId, pageable).map(film -> mapper.map(film, FilmDTO.class));
    }
}
