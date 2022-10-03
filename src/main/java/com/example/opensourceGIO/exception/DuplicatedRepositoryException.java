package com.example.opensourceGIO.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class DuplicatedRepositoryException extends RuntimeException{
    public DuplicatedRepositoryException(String organization, String repository) {
        super(String.format("Organization: %s Repository:%s already exist. ", organization, repository));
    }
}