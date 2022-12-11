package com.librarian.spring.controller;


import com.librarian.spring.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.librarian.spring.dto.BookDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*")
public class DataController {

    private final BookService bookService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto.testInfo>> getTest() {
        return ResponseEntity.ok(bookService.getTestInfo());
    }

}
