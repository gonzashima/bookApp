package com.example.bookApp.mappers;

import com.example.bookApp.dtos.BookDto;
import com.example.bookApp.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookMapper implements Function<BookDto, Book> {

    private final AuthorMapper authorMapper;

    @Autowired
    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    @Override
    public Book apply(BookDto bookDto) {
        return new Book(bookDto.title(), authorMapper.apply(bookDto.author()), bookDto.publishedDate(), bookDto.price());
    }
}
