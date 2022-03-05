package com.javasql.custom.query.service;

import com.javasql.custom.query.constant.Constant;
import com.javasql.custom.query.dto.request.BookRequest;
import com.javasql.custom.query.dto.response.BookPageRes;
import com.javasql.custom.query.dto.response.BookResponse;
import com.javasql.custom.query.dto.response.PageRes;
import com.javasql.custom.query.entity.Book;
import com.javasql.custom.query.exception.ResourceNotFoundException;
import com.javasql.custom.query.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    public BookService() {
    }

    public List<Book> getBooks(){

        List<Book> bookList = new ArrayList<>();

        bookRepository.findAll()
                .forEach(book -> bookList.add(book));
        return bookList;
    }

    public List<Book> getBookByQuery(Set<Integer> yop, String bookType) {
        List<Book> bookList = new ArrayList<>();

        if (yop == null){
            bookRepository.findAll().forEach(book -> bookList.add(book));
        }else {
            return bookRepository.findAllByYearOfPublicationInAndBookType(yop, bookType);
        }
        return bookList;
    }

    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    public Book getBookById(Integer id){
       Book book = bookRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Book not existed with id" + id));

       return book;
    }

    public ResponseEntity<Book> updateBook(Integer id, BookRequest req){
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book not existed with id:" + id));

        BeanUtils.copyProperties(req, book);

        book = bookRepository.save(book);

        return ResponseEntity.ok(book);
    }

    public void deleteBook(Integer id) {

        Book book = bookRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Book not existed with id:" + id));
      bookRepository.delete(book);
    }

    //raw query get book
    public List<Book> getBookByRawQuery(Set<Integer> yop, String bookType) {
        List<Book> bookList = bookRepository.getBooksByYearOfPublication(yop, bookType);
        return bookList;
    }

    public BookPageRes getBooksWithPagination(Set<Integer> yop, int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Book> books = bookRepository.findBookList(yop, paging);

            List<BookResponse> data = books
                    .stream()
                    .map(lst -> {
                        BookResponse bookResponse = new BookResponse();
                        BeanUtils.copyProperties(lst, bookResponse);
                        return bookResponse;
                    }).collect(Collectors.toList());
            
            PageRes pageRes = new PageRes(books.getNumber(), books.getTotalElements(), books.getTotalPages());
            return new BookPageRes(data, pageRes);
        }catch (Exception ex){
            throw ex;
        }
    }
}
