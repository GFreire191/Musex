package com.musex.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Utility {
    public static ResponseStatusException  throwNotFound(String entity) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, entity + " NOT FOUND");
    }
}
