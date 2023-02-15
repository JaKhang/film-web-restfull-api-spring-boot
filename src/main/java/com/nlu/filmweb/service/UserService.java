package com.nlu.filmweb.service;


import com.nlu.filmweb.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findOneById(Long id);
}
