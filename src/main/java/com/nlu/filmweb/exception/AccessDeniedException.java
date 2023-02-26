package com.nlu.filmweb.exception;

import com.nlu.filmweb.payload.response.APIResponse;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends  RuntimeException implements ApiException {
    @Override
    public APIResponse getAPIResponse() {
        return new APIResponse(false, this.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }
}


