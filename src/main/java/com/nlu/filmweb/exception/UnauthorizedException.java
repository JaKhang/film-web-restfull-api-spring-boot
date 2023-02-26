package com.nlu.filmweb.exception;

import com.nlu.filmweb.payload.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception implements ApiException {
    @Override
    public APIResponse getAPIResponse() {
        return new APIResponse(false, this.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
