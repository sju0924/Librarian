package com.librarian.spring.dto;

import com.librarian.spring.model.genreBookClass;
import com.librarian.spring.model.genreClass;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

public class genreDto {
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class genreInfo {

        private String major;
        private String minor;
        private String loanNum;


        public static genreDto.genreInfo of(genreClass d) {
            return genreInfo.builder()
                    .major(d.getMajor())
                    .minor((d.getMinor()))
                    .loanNum(d.getLoanNum())
                    .build();
        }

        public static List<genreDto.genreInfo> of(List<genreClass> details) {
            return details.stream().map(genreDto.genreInfo::of).collect(Collectors.toList());
        }
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class genreBookInfo {

        private String name;
        private String loanNum;


        public static genreDto.genreBookInfo of(genreBookClass d) {
            return genreBookInfo.builder()
                    .name(d.getName())
                    .loanNum(d.getLoanNum())
                    .build();
        }

        public static List<genreDto.genreBookInfo> of(List<genreBookClass> details) {
            return details.stream().map(genreDto.genreBookInfo::of).collect(Collectors.toList());
        }
    }

}
