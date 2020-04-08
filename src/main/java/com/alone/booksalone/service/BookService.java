package com.alone.booksalone.service;

import com.alone.booksalone.BooksaloneApplication;
import com.alone.booksalone.dao.BookDAO;
import com.alone.booksalone.model.Book;
import com.alone.booksalone.model.BookStatus;
import com.sun.javafx.util.Logging;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.ServiceMode;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks(){
        return bookDAO.selectAll();
    }
    public int addBook(Book book){
        return bookDAO.addBook(book);
    }

    public int  deleteBook(int id){
        return bookDAO.deleteBook(id);
    }

    public int recoverBook(int id){
        return bookDAO.updateBookStatus(id, BookStatus.NORMAL);
    }



}
