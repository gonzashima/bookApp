package com.example.bookApp.mappers;

import com.example.bookApp.dtos.AuthorDto;
import com.example.bookApp.model.Author;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AuthorMapper implements Function<AuthorDto, Author> {
    @Override
    public Author apply(AuthorDto authorDto) {
        return new Author(authorDto.name());
    }
}
