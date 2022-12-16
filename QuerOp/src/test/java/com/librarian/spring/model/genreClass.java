package com.librarian.spring.model;
import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class genreClass {

    @Column
    private String major;
    @Column
    private String minor;
    @Column
    private String loanNum;
}
