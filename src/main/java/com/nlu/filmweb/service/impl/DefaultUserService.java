package com.nlu.filmweb.service.impl;

import com.nlu.filmweb.entity.User;
import com.nlu.filmweb.repository.UserRepository;
import com.nlu.filmweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOneById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
