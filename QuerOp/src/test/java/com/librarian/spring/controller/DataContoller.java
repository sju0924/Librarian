package com.librarian.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DataContoller {
    @Controller
    @RequiredArgsConstructor
    public class MainController {
        //    private final MainService mainService;
//
        @GetMapping("/")
        @ResponseStatus(HttpStatus.OK)
        public String mainPage() {
            return "index.html";
        }
    }
}
