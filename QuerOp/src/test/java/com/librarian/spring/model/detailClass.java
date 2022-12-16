package com.librarian.spring.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class detailClass {

    @Column
    private String originalTitle;
    @Column
    private String originalAuthor;
    @Column
    private String originalPublisher;
    @Column
    private String pubYear;
    @Column
    private String loanStatus;
    @Column
    private String libName;
    @Column
    private String isbn;
    @Column
    private String bookKey;
    @Column
    private String speciesKey;

}
