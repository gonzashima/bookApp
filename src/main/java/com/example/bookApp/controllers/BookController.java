package com.example.bookApp.controllers;

import com.example.bookApp.dtos.AuthorDto;
import com.example.bookApp.services.BookService;
import com.example.bookApp.dtos.BookDto;
import com.example.bookApp.dtos.UpdatePriceRequest;
import com.example.bookApp.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) @Valid AuthorDto author) {
        if (author != null) {
            return ResponseEntity.ok(bookService.getBooksByAuthor(author));
        }
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookDto bookDto) {
        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> setPriceById(@PathVariable Long id, @RequestBody @Valid UpdatePriceRequest updatePriceRequest) {
        return ResponseEntity.ok(bookService.setPriceById(id, updatePriceRequest.price()));
    }

}
