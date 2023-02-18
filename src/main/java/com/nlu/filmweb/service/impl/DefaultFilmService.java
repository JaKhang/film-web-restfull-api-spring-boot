package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.payload.request.FilmRequest;
import com.nlu.filmweb.payload.response.FilmResponse;
import com.nlu.filmweb.payload.response.FilmDetailsResponse;
import com.nlu.filmweb.payload.SourcePayload;
import com.nlu.filmweb.entity.Film;
import com.nlu.filmweb.entity.SourceFilm;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.repository.*;
import com.nlu.filmweb.service.FilmService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<FilmResponse> getAll() {
        Type listType = new TypeToken<List<FilmResponse>>() {
        }.getType();
        return mapper.map(filmRepository.findAll(), listType);
    }

    @Override
    public FilmDetailsResponse deleteById(Long id) {
        var film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FILM, ID, id));
        filmRepository.deleteById(id);
        return mapper.map(film, FilmDetailsResponse.class);
    }

    @Override
    public FilmDetailsResponse insert(FilmRequest filmRequest) {
        Film film = mapFilm(filmRequest);
        film = filmRepository.save(film);
        return mapper.map(film, FilmDetailsResponse.class);
    }

    @Override
    public FilmDetailsResponse update(Long id, FilmRequest filmRequest) {
        if(!filmRepository.existsById(id))
            throw new ResourceNotFoundException(FILM, ID, id);
        Film film = mapFilm(filmRequest);
        film.setId(id);
        film = filmRepository.save(film);
        return mapper.map(film, FilmDetailsResponse.class);

    }
    private Film mapFilm(FilmRequest filmRequest){
        var country = countryRepository.findById(filmRequest.getCountryId()).orElse(null);
        var categories = categoryRepository.findAllById(filmRequest.getCategoryIds());
        var actors = actorRepository.findAllById(filmRequest.getActorIds());
        var quality = qualityRepository.findById(filmRequest.getQualityId()).orElse(null);
        var language = languageRepository.findById(filmRequest.getLanguageId()).orElse(null);
        var status = statusRepository.findById(filmRequest.getStatusId()).orElse(null);
        var director = directorRepository.findById(filmRequest.getDirectorId()).orElse(null);
        var producer = producerRepository.findById(filmRequest.getProducerId()).orElse(null);
        var film = mapper.map(filmRequest, Film.class);
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
    public FilmDetailsResponse getById(Long id) {
        var film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FILM, ID, id));
        System.out.println(film);
        return mapper.map(film, FilmDetailsResponse.class);
    }

    @Override
    public Page<FilmResponse> getAll(Pageable pageable) {
        return filmRepository.findAll(pageable).map(film -> mapper.map(film, FilmResponse.class));
    }

    @Override
    public SourcePayload addSourceFilm(Long filmId, SourcePayload sourcePayload) {
        var film = filmRepository.findById(filmId).orElseThrow(() -> new ResourceNotFoundException(FILM, ID, filmId));
        var sourceFilm = mapper.map(sourcePayload, SourceFilm.class);
        sourceFilm.setFilms(film);
        return mapper.map(sourceFilmRepository.save(sourceFilm), SourcePayload.class);
    }

    @Override
    public Page<FilmResponse> getAllByCategory(Long categoryId, Pageable pageable) {
        if(categoryRepository.existsById(categoryId))
            throw new  ResourceNotFoundException(CATEGORY, ID, categoryId);
        return filmRepository.findFilmsByCategoriesId(categoryId, pageable).map(film -> mapper.map(film, FilmResponse.class));
    }
}
