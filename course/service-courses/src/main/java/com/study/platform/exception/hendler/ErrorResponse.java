package com.study.platform.exception.hendler;

import java.util.Map;

public class ErrorResponse {

    public String description;

    public Map<String,String> errors;

    public ErrorResponse(String description, Map<String, String> errors) {

        this.description = description;
        this.errors = errors;
    }
}
