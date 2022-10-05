package com.example.opensourcegio.repositories;

import com.example.opensourcegio.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface RepositoryRepository extends PagingAndSortingRepository<Repository, Integer> {
    List<Repository> findAll();

    Optional<Repository> findByOrganizationAndRepository(String organization, String repository);
}
