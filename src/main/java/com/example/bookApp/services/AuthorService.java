package com.example.bookApp.services;

import com.example.bookApp.dtos.AuthorDto;
import com.example.bookApp.dtos.MessageDto;
import com.example.bookApp.mappers.AuthorMapper;
import com.example.bookApp.model.Author;
import com.example.bookApp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isEmpty())
            throw new IllegalStateException("Author does not exist");

        return optional.get();
    }

    public Author getAuthorByName(AuthorDto authorDto) {
        Optional<Author> optional = authorRepository.findAuthorByName(authorDto.name());

        if (optional.isEmpty())
            throw new IllegalStateException("Author does not exist");

        return optional.get();
    }

    public Author addAuthor(AuthorDto authorDto) {
        if (authorRepository.existsByName(authorDto.name()))
            throw new IllegalStateException("Author already exist");

        return authorRepository.save(authorMapper.apply(authorDto));
    }

    public void addAuthorNoException(AuthorDto authorDto) {
        if (!authorRepository.existsByName(authorDto.name()))
            authorRepository.save(authorMapper.apply(authorDto));
    }

    public MessageDto deleteById(Long id) {
        if (!authorRepository.existsById(id))
            throw new IllegalStateException("Author does not exist");

        authorRepository.deleteById(id);
        return new MessageDto("Author with id: " + id + " deleted successfully");
    }

}
