package com.example.Lib.demoLibrary.starter;

import com.example.Lib.demoLibrary.domain.Book;
import com.example.Lib.demoLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationStarter implements CommandLineRunner {
    JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("myRepo")
    BookRepository bookRepository;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void run(String... args) throws Exception {
        createBookTable();

        bookRepository.save(
                new Book("LLL","DDD",12,"Fantasy","goodBook")
        );
        bookRepository.save(
                new Book("trLoLoLo","sDdDsD",1212,"FantasyDed","niceBook"));
        bookRepository.save(
                new Book("LoLoLo","DfDfD",1313,"Science","badBook"));

        List<Book>bookList = bookRepository.findAll();

        for(Book book : bookList){
            System.out.printf("book user %d. %s%n",book.getId(),bookRepository.findById(book.getId()));
        }
    }

    private void createBookTable(){
        jdbcTemplate.execute("DROP TABLE IF EXISTS my_lib");
        jdbcTemplate.execute("""
        CREATE TABLE my_lib(
          id SERIAL,
          title varchar(255),
          author varchar(255),
          publication_year INT,
          genre varchar(255),
          description varchar(255)
        )
        """
        );
    }
}
