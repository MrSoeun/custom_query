package com.javasql.custom.query.repository;

import com.javasql.custom.query.dto.response.BookResponse;
import com.javasql.custom.query.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByYearOfPublicationInAndBookType(Set<Integer> yop, String bookType);

    String rawQuery = "select * from book where year_of_publication IN ?1 and book_type = ?2";

    @Query(nativeQuery = true, value = rawQuery)
    List<Book> getBooksByYearOfPublication(Set<Integer> yop, String bookType);

//    Page<Book> findAll(Pageable pageable);
    String rawQuery2 = "select * from book where year_of_publication IN ?1";
    @Query(nativeQuery = true, value = rawQuery2)
    Page<Book> findBookList(Set<Integer> yop, Pageable pageable);
}
