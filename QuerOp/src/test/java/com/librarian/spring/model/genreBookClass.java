package com.librarian.spring.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class genreBookClass {

    @Column
    private String name;
    @Column
    private String loanNum;
}
