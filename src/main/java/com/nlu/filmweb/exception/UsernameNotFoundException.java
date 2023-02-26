package com.nlu.filmweb.exception;

import com.nlu.filmweb.payload.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.nlu.filmweb.utils.AppConstant.USER;
import static com.nlu.filmweb.utils.AppConstant.USER_NAME;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends ResourceNotFoundException{


    public UsernameNotFoundException(String username) {
        super(USER, USER_NAME , username);
    }

}
