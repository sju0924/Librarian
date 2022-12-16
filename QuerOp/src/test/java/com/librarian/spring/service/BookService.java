package com.librarian.spring.service;
import com.librarian.spring.dto.detailDto;
import com.librarian.spring.dto.genreDto;
import com.librarian.spring.model.*;
import com.librarian.spring.dto.BookDto;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    public static BookService INSTANCE = new BookService();

    private static Socket socket;

    private BookService(){
        try {
            socket = new Socket("163.180.118.210", 57621);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    public List<BookDto.bookInfo> getSearchByAttribute(String value, String method){
        List<BookClass> bookList = new ArrayList<BookClass>();

        try {

            JSONObject request = new JSONObject();
            request.put("ServiceRequest","SearchByAttribute");
            request.put("FeatureRequest", value);
            request.put("SearchData",method);
            System.out.print(request);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            out.write(request.toString().getBytes());
            out.flush();

            byte[] inputData = new byte[100000];
            int length = in.read(inputData, 0, 4);
            int totalLen = ByteBuffer.wrap(inputData).getInt();

            String inputMessage = new String();
            while(length < totalLen){
                totalLen -= length;
                length = in.read(inputData);
                inputMessage += new String(inputData,0,length,"utf-8");
            }

            System.out.print(inputMessage);
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(inputMessage);

            System.out.print(jsonObj.toString());

            JSONArray arr = (JSONArray) jsonObj.get("data");


            ArrayList<String> buf = new ArrayList<>();
            for (int i = 0; i < arr.size(); ++i) {
                BookClass b = new BookClass();
                JSONObject rec = (JSONObject) arr.get(i);

                if(buf.indexOf(rec.get("title")) > -1){
                    continue;
                }

                buf.add((String)rec.get("title"));
                b.setTitle((String) rec.get("title"));
                b.setAuthor((String) rec.get("author"));
                b.setMajor((String) rec.get("major category"));
                b.setMinor((String) rec.get("minor category"));
                b.setYear((String) rec.get("Year of issue"));
                b.setPublisher((String) rec.get("publisher"));

                bookList.add(b);

            }




        }
        catch(Exception e){
            e.printStackTrace();
        }

        return BookDto.bookInfo.of(bookList);
    }

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

    public List<detailDto.detailInfo> getDetailPage(String title){
        List<detailClass> detailList = new ArrayList<detailClass>();
        try {
            JSONObject request = new JSONObject();
            request.put("ServiceRequest","SearchByBook");
            request.put("SearchData", title);
            System.out.print(request);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            out.write(request.toString().getBytes());
            out.flush();

            byte[] inputData = new byte[100000];
            int length = in.read(inputData, 0, 4);
            int totalLen = ByteBuffer.wrap(inputData).getInt();

            String inputMessage = new String();
            while(length < totalLen){
                totalLen -= length;
                length = in.read(inputData);
                inputMessage += new String(inputData,0,length,"utf-8");
            }


            System.out.print(inputMessage);
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(inputMessage);

            System.out.print(jsonObj.toString());

            JSONArray arr = (JSONArray) jsonObj.get("data");

            ArrayList<String> buf = new ArrayList<>();
            for (int i = 0; i < arr.size(); ++i) {
                detailClass b = new detailClass();
                JSONObject rec = (JSONObject) arr.get(i);

                b.setOriginalTitle((String) rec.get("originalTitle"));
                b.setOriginalAuthor((String) rec.get("originalAuthor"));
                b.setOriginalPublisher((String) rec.get("originalPublisher"));
                b.setPubYear((String) rec.get("pubYear"));
                b.setLoanStatus((String) rec.get("loanStatus"));
                b.setLibName((String) rec.get("libName"));
                b.setIsbn((String) rec.get("isbn"));
                b.setBookKey((String) rec.get("bookKey"));
                b.setSpeciesKey((String) rec.get("speciesKey"));

                detailList.add(b);

            }

            System.out.print(title);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return detailDto.detailInfo.of(detailList);
    }

    public List<genreDto.genreInfo> getGenreRanking(){
        List<genreClass> genreList = new ArrayList<genreClass>();

        try {


            JSONObject request = new JSONObject();
            request.put("ServiceRequest","RankingGenre");
            System.out.print(request);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            out.write(request.toString().getBytes());
            out.flush();

            byte[] inputData = new byte[100000];
            int length = in.read(inputData, 0, 4);
            int totalLen = ByteBuffer.wrap(inputData).getInt();

            String inputMessage = new String();
            while(length < totalLen){
                totalLen -= length;
                length = in.read(inputData);
                inputMessage += new String(inputData,0,length,"utf-8");
            }

            System.out.print(inputMessage);
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(inputMessage);

            System.out.print(jsonObj.toString());

            JSONArray arr = (JSONArray) jsonObj.get("data");

            for (int i = 0; i < arr.size(); ++i) {
                genreClass b = new genreClass();
                JSONObject rec = (JSONObject) arr.get(i);

                b.setMajor((String) rec.get("KDC0"));
                b.setMinor((String) rec.get("KDC1"));
                b.setLoanNum((String) rec.get("Loan number"));

                genreList.add(b);

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }


        return genreDto.genreInfo.of(genreList);
    }
    public List<genreDto.genreBookInfo> getBookRankingByGenre(String major, String minor){
        List<genreBookClass> genreBookList = new ArrayList<genreBookClass>();

        try {

            JSONObject request = new JSONObject();
            request.put("ServiceRequest","RankingBookGenre");
            request.put("major", major);
            request.put("minor",minor);
            System.out.print(request);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            out.write(request.toString().getBytes());
            out.flush();

            byte[] inputData = new byte[100000];
            int length = in.read(inputData, 0, 4);
            int totalLen = ByteBuffer.wrap(inputData).getInt();

            String inputMessage = new String();
            while(length < totalLen){
                totalLen -= length;
                length = in.read(inputData);
                inputMessage += new String(inputData,0,length,"utf-8");
            }


            System.out.print(inputMessage);
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(inputMessage);

            System.out.print(jsonObj.toString());

            JSONArray arr = (JSONArray) jsonObj.get("data");


            for (int i = 0; i < arr.size(); ++i) {
                genreBookClass b = new genreBookClass();
                JSONObject rec = (JSONObject) arr.get(i);

                b.setName((String) rec.get("name"));
                b.setLoanNum((String) rec.get("Loan number"));

                genreBookList.add(b);

            }




        }
        catch(Exception e){
            e.printStackTrace();
        }



        return genreDto.genreBookInfo.of(genreBookList);
    }

    public List<BookDto.bookInfo> getNewBook(String year){
        List<BookClass> bookList = new ArrayList<BookClass>();

        try {

            JSONObject request = new JSONObject();
            request.put("ServiceRequest","SearchNew");
            request.put("SearchData", year);
            System.out.print(request);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            out.write(request.toString().getBytes());
            out.flush();

            byte[] inputData = new byte[100000];
            int length = in.read(inputData, 0, 4);
            int totalLen = ByteBuffer.wrap(inputData).getInt();

            String inputMessage = new String();
            while(length < totalLen){
                totalLen -= length;
                length = in.read(inputData);
                inputMessage += new String(inputData,0,length,"utf-8");
            }


            System.out.print(inputMessage);
            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(inputMessage);

            System.out.print(jsonObj.toString());

            JSONArray arr = (JSONArray) jsonObj.get("data");

            ArrayList<String> buf = new ArrayList<>();
            for (int i = 0; i < arr.size(); ++i) {
                BookClass b = new BookClass();
                JSONObject rec = (JSONObject) arr.get(i);

                if(buf.indexOf(rec.get("title")) > -1){
                    continue;
                }

                buf.add((String)rec.get("title"));
                b.setTitle((String) rec.get("title"));
                b.setAuthor((String) rec.get("author"));
                b.setMajor((String) rec.get("major category"));
                b.setMinor((String) rec.get("minor category"));
                b.setYear((String) rec.get("Year of issue"));
                b.setPublisher((String) rec.get("publisher"));

                bookList.add(b);

            }




        }
        catch(Exception e){
            e.printStackTrace();
        }

        return BookDto.bookInfo.of(bookList);
    }

}