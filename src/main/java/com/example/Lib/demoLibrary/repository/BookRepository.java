package com.example.Lib.demoLibrary.repository;

import com.example.Lib.demoLibrary.domain.Book;

import java.util.List;

public interface BookRepository {
    int count();
    int save(Book book);
    int update(Book book);
    int deleteById(Long id);
    Book findById(Long id);
    List<Book>findAll();
}
