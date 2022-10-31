package com.example.opensourcegio.worker.clients.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Repository {

    private String organization;

    private String repository;
}
