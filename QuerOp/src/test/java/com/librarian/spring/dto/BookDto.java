package com.librarian.spring.dto;

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
        private Integer year;

        public static testInfo of(testClass test) {
            return testInfo.builder()
                    .ISBN(test.getISBN())
                    .title(test.getTitle())
                    .author(test.getAuthor())
                    .year(test.getYear())
                    .build();
        }

        public static List<testInfo> of(List<testClass> tests) {
            return tests.stream().map(testInfo::of).collect(Collectors.toList());
        }
    }
}