package com.example.Lib.demoLibrary.repository;

import com.example.Lib.demoLibrary.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum)throws SQLException{
        return new Book(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("publication_year"),
                rs.getString("genre"),
                rs.getString("description")
        );
    }

}
