package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class UserException extends  RuntimeException{

    private final ErrorType errorType;
    public UserException(ErrorType errorType) {
        this.errorType = errorType;
    }
    public UserException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }

}
