package com.anar.librarymanagementapi.controller;

import com.anar.librarymanagementapi.model.AuthorModel;
import com.anar.librarymanagementapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private AuthorService authorService ;

    @PostMapping
    public ResponseEntity<AuthorModel> createAuthor(@RequestBody AuthorModel request) {
        AuthorModel created = authorService.createAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping
    public ResponseEntity<List<AuthorModel>> getAllAuthors() {
        List<AuthorModel> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }
}
