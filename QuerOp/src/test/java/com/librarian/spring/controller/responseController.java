package com.librarian.spring.controller;


import com.librarian.spring.dto.genreDto;
import com.librarian.spring.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.librarian.spring.dto.BookDto;
import com.librarian.spring.dto.detailDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*")
public class responseController {

    private final static BookService bookService = BookService.INSTANCE;


    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto.testInfo>> getTest() {
        return ResponseEntity.ok(bookService.getTestInfo());
    }

    @GetMapping("/detail/{title}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<detailDto.detailInfo>> getDetail(@PathVariable("title") String Title) {
        return ResponseEntity.ok(bookService.getDetailPage(Title));
    }

    @GetMapping("/attribute/{value}/{method}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto.bookInfo>> getAttribute(@PathVariable("value") String Value, @PathVariable("method") String Method) {
        return ResponseEntity.ok(bookService.getSearchByAttribute(Value, Method));
    }

    @GetMapping("/ranking/genre")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<genreDto.genreInfo>> getGenreRanking() {
        return ResponseEntity.ok(bookService.getGenreRanking());
    }
    @GetMapping("/ranking/genre/{major}/{minor}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<genreDto.genreBookInfo>> getGenreBookRanking(@PathVariable("major") String Major, @PathVariable("minor") String Minor) {
        return ResponseEntity.ok(bookService.getBookRankingByGenre(Major, Minor));
    }

    @GetMapping("/new/{year}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto.bookInfo>> getNewBook(@PathVariable("year") String Year) {
        return ResponseEntity.ok(bookService.getNewBook(Year));
    }
}
