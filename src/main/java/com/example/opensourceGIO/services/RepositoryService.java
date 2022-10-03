package com.example.opensourceGIO.services;

import com.example.opensourceGIO.exception.DuplicatedRepositoryException;
import com.example.opensourceGIO.models.Repository;
import com.example.opensourceGIO.repositories.RepositoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;

    public RepositoryService(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    @Transactional
    public void create(String organization, String repository) {
        this.validate(organization, repository);
        Repository r = Repository.builder().
                organization(organization).repository(repository).build();

        this.repositoryRepository.save(r);
    }

    public List<Repository> list() {
        return repositoryRepository.findAll();
    }

    private void validate(String organization, String repository) {
        this.repositoryRepository.findByOrganizationAndRepository(organization, repository).ifPresent((r) ->
        {throw new DuplicatedRepositoryException(organization, repository);
        });
    }
}
