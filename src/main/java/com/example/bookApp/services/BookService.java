package com.example.bookApp.services;

import com.example.bookApp.dtos.AuthorDto;
import com.example.bookApp.dtos.MessageDto;
import com.example.bookApp.mappers.BookMapper;
import com.example.bookApp.model.Author;
import com.example.bookApp.repositories.BookRepository;
import com.example.bookApp.dtos.BookDto;
import com.example.bookApp.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorService = authorService;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(AuthorDto author) {
        return bookRepository.findAllByAuthorName(author.name());
    }

    public Book getBookById(Long id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty())
            throw new IllegalStateException("Book not found");

        return optional.get();
    }

    public Book addBook(BookDto bookDto) {
        authorService.addAuthorNoException(bookDto.author());
        if (bookRepository.findBookByTitle(bookDto.title()).isPresent())
            throw new IllegalStateException("Book already in DB");

        Book book = bookMapper.apply(bookDto);
        Author author = authorService.getAuthorByName(bookDto.author());

        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public MessageDto deleteBookById(Long id) {
        boolean exists = bookRepository.existsById(id);
        if (!exists)
            throw new IllegalStateException("Book does not exist");

        bookRepository.deleteById(id);
        return new MessageDto("Book with id: " + id + " deleted successfully");
    }

    public Book setPriceById(Long id, BigDecimal price) {
        if (price == null)
            throw new IllegalStateException("Price is null");

        Optional<Book> optionBook = bookRepository.findById(id);
        if (optionBook.isEmpty())
            throw new IllegalStateException("Book does not exist");

        Book book = optionBook.get();
        book.setPrice(price.abs());
        return bookRepository.save(book);
    }
}
