package com.nlu.filmweb.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@Data
public class APIResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 7702134516418120340L;

    private Boolean success;

    private String message;

    private Integer status;

    public APIResponse() {

    }
    public APIResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public APIResponse(Boolean success, String message, Integer statusCode) {
        this.success = success;
        this.message = message;
        this.status = statusCode;
    }
}
