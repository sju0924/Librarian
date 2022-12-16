package com.librarian.spring.dto;

import com.librarian.spring.model.detailClass;
import com.librarian.spring.model.testClass;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

public class detailDto {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class detailInfo {

        private String originalTitle;
        private String originalAuthor;
        private String originalPublisher;
        private String pubYear;
        private String loanStatus;
        private String libName;
        private String isbn;
        private String bookKey;
        private String speciesKey;


        public static detailInfo of(detailClass d) {
            return detailInfo.builder()
            .originalTitle(d.getOriginalTitle())
            .originalAuthor((d.getOriginalAuthor()))
            .originalPublisher(d.getOriginalPublisher())
            .pubYear(d.getPubYear())
            .loanStatus(d.getLoanStatus())
            .libName(d.getLibName())
            .isbn((d.getIsbn()))
            .bookKey(d.getBookKey())
            .speciesKey(d.getSpeciesKey())
            .build();
        }

        public static List<detailInfo> of(List<detailClass> details) {
            return details.stream().map(detailInfo::of).collect(Collectors.toList());
        }
    }
}