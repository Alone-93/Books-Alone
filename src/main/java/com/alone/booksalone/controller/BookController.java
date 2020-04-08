package com.alone.booksalone.controller;

import com.alibaba.druid.util.StringUtils;
import com.alone.booksalone.model.Book;
import com.alone.booksalone.model.Ticket;
import com.alone.booksalone.model.User;
import com.alone.booksalone.service.BookService;
import com.alone.booksalone.service.TicketService;
import com.alone.booksalone.service.UserService;
import com.alone.booksalone.util.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @RequestMapping(path = {"/index"}, method = {RequestMethod.GET})
    public String bookList(Model model, HttpServletRequest request){


        //从cookie中获取ticket
        String ticket= CookieUtils.getCookie("t",request);
        if(ticket!=null) {
            Ticket tickets = ticketService.getTicket(ticket);
            if (tickets!=null) {
                User host = userService.getUser(tickets.getUser_id());
                if (host!=null){
                    model.addAttribute("host",host);
                }
            }
        }

        loadAllBooksView(model);
        return "/book/books";
    }

    @RequestMapping(path = {"/books/add"}, method = {RequestMethod.GET})
    public String addBook() {
        return "book/addbook";
    }


    @RequestMapping(path = {"/books/add/do"}, method = {RequestMethod.POST})
    public String doAddBook(
            @RequestParam("name") String name,
            @RequestParam("author") String author,
            @RequestParam("price") String price
    ) {

        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        bookService.addBook(book);

        return "redirect:/index";

    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/delete"}, method = {RequestMethod.GET})
    public String deleteBook(
            @PathVariable("bookId") int bookId
    ) {

        bookService.deleteBook(bookId);
        return "redirect:/index";

    }

    @RequestMapping(path = {"/books/{bookId:[0-9]+}/recover"}, method = {RequestMethod.GET})
    public String recoverBook(
            @PathVariable("bookId") int bookId
    ) {

        bookService.recoverBook(bookId);
        return "redirect:/index";

    }

    /**
     * 为model加载所有的book
     */
    private void loadAllBooksView(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
    }

}
