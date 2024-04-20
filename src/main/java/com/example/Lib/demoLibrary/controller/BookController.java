package com.example.Lib.demoLibrary.controller;

import com.example.Lib.demoLibrary.domain.Book;
import com.example.Lib.demoLibrary.repository.JdbcBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final JdbcBookRepository jdbcBookRepository;

    @Autowired BookController(JdbcBookRepository jdbcBookRepository){
        this.jdbcBookRepository = jdbcBookRepository;
    }

    @PostMapping("/save")
    public Book save(@RequestBody Book book){
        jdbcBookRepository.save(book);
        return book;
    }
    @GetMapping("/all")
    public List<Book>findAll(){
        return jdbcBookRepository.findAll();
    }
}
