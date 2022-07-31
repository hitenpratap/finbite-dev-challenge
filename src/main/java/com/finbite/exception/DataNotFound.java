package com.finbite.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataNotFound extends RuntimeException {

    public DataNotFound(String message) {
        super(message);
    }

}
