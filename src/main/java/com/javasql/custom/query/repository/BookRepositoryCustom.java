package com.javasql.custom.query.repository;

import com.javasql.custom.query.entity.Book;

import java.util.List;

public interface BookRepositoryCustom {

    public List<Book> getAllBookByQueryDsl(Integer year);

//    public List<BookQueryDslDTO> getAllBooksByQueryDslDto(Integer year);
}
