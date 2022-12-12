package com.librarian.spring.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class testClass {
    @Id
    private int ISBN;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private Integer year;
}
