package com.example.Lib.demoLibrary.repository;

import com.example.Lib.demoLibrary.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("myRepo")
public class JdbcBookRepository implements BookRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBookRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from my_lib",Integer.class);
    }

    @Override
    public int save(Book book) {
        return  jdbcTemplate.update("""
                insert into my_lib(title,author,publication_year,genre,description)
                values(?,?,?,?,?)
                """,book.getTitle(),book.getAuthor(),book.getYear(),
                book.getGenre(),book.getDescription());
    }

    @Override
    public int update(Book book) {
        return jdbcTemplate.update("""
                update my_lib
                set title = ?,
                    author = ?,
                    publication_year = ?,
                    genre = ?,
                    description = ?
                where id = ?
                """,book.getTitle(),book.getAuthor(),book.getYear(),
                book.getGenre(),book.getDescription(),book.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete my_lib where id = ?",id);
    }

    @Override
    public Book findById(Long id) {
        List<Book>book = jdbcTemplate.query("""
                select * from my_lib m where m.id = ?
                """, new BookRowMapper(),id);

        if(book.isEmpty()){
            return null;
        }
        return book.get(0);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("select * from my_lib",new BookRowMapper());
    }
}
