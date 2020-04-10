package com.alone.booksalone;

import com.alone.booksalone.dao.BookDAO;
import com.alone.booksalone.dao.TicketDAO;
import com.alone.booksalone.dao.UserDAO;
import com.alone.booksalone.model.Book;
import com.alone.booksalone.model.Ticket;
import com.alone.booksalone.model.User;
import com.alone.booksalone.service.LoginService;
import com.alone.booksalone.service.TicketService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;
import java.util.List;

@SpringBootTest
class BooksaloneApplicationTests {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private TicketService ticketService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Test
    void contextLoads() {

        Ticket ticket=ticketService.getTicket("ecf0d558aaf05a417ca82afa8a0929629864");
        logger.info("aaaaa:"+ticket.getUser_id()+ticket.getId());

    }

    @Test
    public void bookDAOTest(){
        List<Book> books=bookDAO.selectAll();
        System.out.println(books);
    }


    @Test
    public void TicketTest(){
        Ticket ticket=new Ticket();
        ticket.setUser_id(1);
        ticket.setTicket("root");
        System.out.println(ticketDAO.addTicket(ticket));

    }

    @Autowired
    private LoginService loginService;
    @Test
    public void loginServiceTest() throws Exception{

        String t=loginService.login("root","root");
        logger.info("t:"+t);
    }





}
