package com.umc.study.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.umc.study.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Map<String, Object>> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Map<String, Object>> getBooksByCategory(long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    public void createBook(Map<String, Object> body) {
        bookRepository.save(body);
    }
}
