package com.example.opensourcegio.exception;

import lombok.Data;
import lombok.ToString;

@ToString(callSuper = true)
public class DuplicatedRepositoryException extends RuntimeException{
    public DuplicatedRepositoryException(String organization, String repository) {
        super(String.format("Organization: %s Repository:%s already exist. ", organization, repository));
    }
}
