package com.example.bookApp.controllers;

import com.example.bookApp.dtos.AuthorDto;
import com.example.bookApp.dtos.MessageDto;
import com.example.bookApp.model.Author;
import com.example.bookApp.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(authorService.getAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody @Valid AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.addAuthor(authorDto), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<MessageDto> deleteAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.deleteById(id));
    }


}
