package com.javasql.custom.query.controller;

import com.javasql.custom.query.dto.request.BookRequest;
import com.javasql.custom.query.dto.response.BookPageRes;
import com.javasql.custom.query.entity.Book;
import com.javasql.custom.query.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getBooks")
    public List<Book> getBooks(){

        return bookService.getBooks();
    }

    /**
     *
     * @param yop
     * @param bookType
     * @return
     *
     */
    //query get all books
    @GetMapping("/getBookByQuery")
    public List<Book> getBooksByQuery(
            @RequestParam(value = "yearOfPublications", required = false) Set<Integer> yop,
            @RequestParam(value = "bookType", required = false) String bookType){
        return bookService.getBookByQuery(yop, bookType);
    }

    /**
     *
     * @param book
     * @return
     */
    @PostMapping("/createBook")
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id){
       Book book = bookService.getBookById(id);

       return ResponseEntity.ok(book);
    }

    /**
     *
     * @param id
     * @param req
     * @return
     */
    @PostMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody BookRequest req){
        ResponseEntity<Book> book = bookService.updateBook(id, req);
        return book;
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete successfully", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    /**
     *
     * @param yop
     * @param bookType
     * @return
     */
    //get book by raw query
    @GetMapping("/getBookByRawQuery")
    public List<Book> getBooksByRawQuery(@RequestParam(value = "yop") Set<Integer> yop, @RequestParam(value = "bookType") String bookType){
        return bookService.getBookByRawQuery(yop, bookType);
    }

    /**
     *
     */
    @GetMapping("/getBooksWithPagination")
    public ResponseEntity<BookPageRes> getBookWithPagination(
            @RequestParam(value = "yop") Set<Integer> yop,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getBooksWithPagination(yop, page, size));
    }
}
