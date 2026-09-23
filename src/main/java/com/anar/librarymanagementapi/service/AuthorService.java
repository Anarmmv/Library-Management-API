package com.anar.librarymanagementapi.service;

import com.anar.librarymanagementapi.entity.Author;
import com.anar.librarymanagementapi.model.AuthorModel;
import com.anar.librarymanagementapi.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorModel createAuthor(AuthorModel request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setEmail(request.getEmail());
        Author saved = authorRepository.save(author) ;
        return toModel(saved) ;
    }
    public List<AuthorModel> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    private AuthorModel toModel(Author author) {
        AuthorModel model = new AuthorModel();
        model.setId(author.getId());
        model.setName(author.getName());
        model.setEmail(author.getEmail());
        return model;
    }
}

