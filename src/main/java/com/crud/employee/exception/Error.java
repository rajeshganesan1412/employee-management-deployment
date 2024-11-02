package com.crud.employee.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Error {

    private String url;
    private Integer httpStatusCode;
    private String message;

    public Error(String url, Integer httpStatusCode, String message) {
        this.url = url;
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

}
