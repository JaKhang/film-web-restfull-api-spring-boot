package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.payload.SourcePayload;
import com.nlu.filmweb.entity.Film;
import com.nlu.filmweb.entity.SourceFilm;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.payload.request.FilmRequest;
import com.nlu.filmweb.payload.response.FilmDetailsResponse;
import com.nlu.filmweb.payload.response.FilmResponse;
import com.nlu.filmweb.repository.*;
import com.nlu.filmweb.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.nlu.filmweb.utils.AppConstant.*;

@Service
@RequiredArgsConstructor
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

    @Override
    public List<FilmResponse> getAll() {
        Type type = new TypeToken<List<FilmResponse>>(){}.getType();
        return mapper.map(filmRepository.findAll(), type);
    }

    @Override
    public FilmDetailsResponse deleteById(Long id) {
        var film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FILM, ID, id));
        var filmResponse = mapper.map(film, FilmDetailsResponse.class);
        sourceFilmRepository.deleteAllById(film.getSourceFilms().stream().map(SourceFilm::getId).toList());
        filmRepository.deleteById(id);
        return filmResponse;
    }

    @Override
    public FilmDetailsResponse insert(FilmRequest filmRequest) {
        Film film = mapFilm(filmRequest);
        Film finalFilm = filmRepository.save(film);
        if(film.getSourceFilms() != null){
            film.getSourceFilms().forEach(sourceFilm ->{
                sourceFilm.setFilms(finalFilm);
                sourceFilmRepository.save(sourceFilm);
            } );
        }

        return mapper.map(film, FilmDetailsResponse.class);
    }

    @Override
    public FilmDetailsResponse update(Long id, FilmRequest filmRequest) {
        var film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FILM, ID, id));
        film = mapFilm(filmRequest);
        film = filmRepository.save(film);
        return mapper.map(film, FilmDetailsResponse.class);

    }
    private Film mapFilm(FilmRequest filmRequest){
        var country = filmRequest.getCountryId() == null ? null : countryRepository.findById(filmRequest.getCountryId()).orElse(null);
        var categories = filmRequest.getCategoryIds() == null ? null : categoryRepository.findAllById(filmRequest.getCategoryIds());
        var actors = filmRequest.getActorIds() == null ? null : actorRepository.findAllById(filmRequest.getActorIds());
        var quality = filmRequest.getQualityId() == null ? null : qualityRepository.findById(filmRequest.getQualityId()).orElse(null);
        var language = filmRequest.getLanguageId() == null ? null : languageRepository.findById(filmRequest.getLanguageId()).orElse(null);
        var status = filmRequest.getStatusId() == null ? null : statusRepository.findById(filmRequest.getStatusId()).orElse(null);
        var director = filmRequest.getDirectorId() == null ? null : directorRepository.findById(filmRequest.getDirectorId()).orElse(null);
        var producer = filmRequest.getProducerId() == null ? null : producerRepository.findById(filmRequest.getProducerId()).orElse(null);
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
        if(!categoryRepository.existsById(categoryId))
            throw new  ResourceNotFoundException(CATEGORY, ID, categoryId);
        return filmRepository.findFilmsByCategoriesId(categoryId, pageable).map(film -> mapper.map(film, FilmResponse.class));
    }

    @Override
    public Page<FilmResponse> searchFullText(String text, Pageable pageable) {
            return filmRepository.searchFilmIdFullText(text, pageable).map(aLong -> mapper.map(filmRepository.findById(aLong).get(), FilmResponse.class));
    }

    @Override
    public Page<FilmResponse> getAllByPublishYear(Integer publishYear, PageRequest pageRequest) {
        return filmRepository.findFilmsByPublishYear(publishYear, pageRequest).map(film -> mapper.map(film, FilmResponse.class));
    }

    @Override
    public Page<FilmResponse> getAllByCategoryAnhPublishYear(Long categoryId, Integer publishYear, PageRequest pageRequest) {
        return filmRepository.findFilmsByCategoriesIdAndPublishYear(categoryId, publishYear, pageRequest).map(film -> mapper.map(film, FilmResponse.class));
    }
}
