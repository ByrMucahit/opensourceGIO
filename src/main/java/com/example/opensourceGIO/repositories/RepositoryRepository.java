package com.example.opensourceGIO.repositories;

import com.example.opensourceGIO.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface RepositoryRepository extends PagingAndSortingRepository<Repository, Integer> {
    List<Repository> findAll();
}
