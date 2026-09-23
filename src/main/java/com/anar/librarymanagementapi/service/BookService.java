package com.anar.librarymanagementapi.service;

import com.anar.librarymanagementapi.entity.Author;
import com.anar.librarymanagementapi.entity.Book;
import com.anar.librarymanagementapi.model.BookModel;
import com.anar.librarymanagementapi.repository.AuthorRepository;
import com.anar.librarymanagementapi.repository.BookRepository;
import com.anar.librarymanagementapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    public BookModel createBook(BookModel request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Author not found with id: " + request.getAuthorId()));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setPublishedYear(request.getPublishedYear());
        book.setAuthor(author);

        Book saved = bookRepository.save(book);

        return toModel(saved);
    }

    public List<BookModel> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
    private BookModel toModel(Book book) {
        BookModel model = new BookModel();
        model.setId(book.getId());
        model.setTitle(book.getTitle());
        model.setPrice(book.getPrice());
        model.setPublishedYear(book.getPublishedYear());

        BookModel.AuthorSummary summary = new BookModel.AuthorSummary(
                book.getAuthor().getId(),
                book.getAuthor().getName()
        );
        model.setAuthor(summary);

        return model;
    }

    public BookModel getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book not found with id: " + id));
        return toModel(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
