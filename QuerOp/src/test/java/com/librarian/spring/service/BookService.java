package com.librarian.spring.service;
import com.librarian.spring.model.testClass;
import com.librarian.spring.repository.TestRepository;
import com.librarian.spring.dto.BookDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {

    private final TestRepository testRepository;


    public List<BookDto.testInfo> getTestInfo(){
        testClass test = new testClass();
        test.setISBN(1234567890);
        test.setTitle("test book");
        test.setAuthor("jeon sohn");
        test.setYear(2022);

        List<testClass> testList = new ArrayList<testClass>();
        testList.add(test);

        return BookDto.testInfo.of(testList);
    }


}