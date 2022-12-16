package com.librarian.spring.dto;

import com.librarian.spring.model.BookClass;
import com.librarian.spring.model.testClass;
import lombok.*;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

public class BookDto {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class testInfo {
        private int ISBN;
        private String title;
        private String author;
        private String genre;
        private Integer year;

        public static testInfo of(testClass test) {
            return testInfo.builder()
                    .ISBN(test.getISBN())
                    .title(test.getTitle())
                    .author(test.getAuthor())
                    .year(test.getYear())
                    .year(test.getGenre())
                    .build();
        }

        public static List<testInfo> of(List<testClass> tests) {
            return tests.stream().map(testInfo::of).collect(Collectors.toList());
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class bookInfo {
        private String title;
        private String author;
        private String year;
        private String major;
        private String minor;
        private String publisher;

        public static bookInfo of(BookClass book) {
            return bookInfo.builder()
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .year(book.getYear())
                    .major(book.getMajor())
                    .minor(book.getMinor())
                    .publisher(book.getPublisher())
                    .build();
        }

        public static List<bookInfo> of(List<BookClass> books) {
            return books.stream().map(bookInfo::of).collect(Collectors.toList());
        }
    }
}