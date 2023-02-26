package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.payload.DirectorPayload;
import com.nlu.filmweb.entity.Director;
import com.nlu.filmweb.exception.ResourceNotFoundException;
import com.nlu.filmweb.repository.DirectorRepository;
import com.nlu.filmweb.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nlu.filmweb.utils.AppConstant.ID;

@Service
@RequiredArgsConstructor
public class DefaultDirectorService implements DirectorService {
    private final DirectorRepository directorRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<DirectorPayload> getAll() {
        return directorRepository.findAll().stream().map(director -> modelMapper.map(director, DirectorPayload.class)).toList();
    }

    @Override
    public DirectorPayload deleteById(Long id) {
        var director = directorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Director", ID, id));
        directorRepository.delete(director);
        return modelMapper.map(director, DirectorPayload.class);
    }

    @Override
    public DirectorPayload insert(DirectorPayload directorPayload) {
        var director = modelMapper.map(directorPayload, Director.class);
        directorRepository.save(director);
        directorPayload.setId(director.getId());
        return directorPayload;
    }

    @Override
    public DirectorPayload update(Long id, DirectorPayload directorPayload) {
        var director = directorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Director", ID, id));
        directorRepository.save(director);
        return modelMapper.map(director, DirectorPayload.class);
    }

    @Override
    public DirectorPayload getById(Long id) {
        var director = directorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Director", ID, id));
        return modelMapper.map(director, DirectorPayload.class);
    }
}
