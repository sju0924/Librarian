package com.librarian.spring.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BookClass {

    @Column
    private String title;
    @Column
    private String year;
    @Column
    private String major;
    @Column
    private String minor;
    @Column
    private String publisher;
    @Column
    private String author;

}
