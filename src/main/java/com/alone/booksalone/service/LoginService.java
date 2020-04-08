package com.alone.booksalone.service;

import com.alone.booksalone.model.Ticket;
import com.alone.booksalone.model.User;
import com.alone.booksalone.model.exception.LoginRegisterException;
import com.alone.booksalone.util.TicketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    /**
     * 登录逻辑,
     * @param email
     * @param password
     * @return 返回最新t票
     * @throws Exception
     */
    public String login(String email,String password)throws Exception{

        User user=userService.getUser(email);
        //登录信息检查
        if (user==null){
            logger.info("邮箱："+email+"不存在");
            throw new LoginRegisterException("邮箱不存在");
        }
        if(!passwordEncoder.matches(password,user.getPassword())){//校验密码
            logger.info("用户："+email+"密码错误");
            throw new LoginRegisterException("密码不正确");
        }

        //检查ticket
        Ticket t=ticketService.getTicket(user.getId());

        //如果没有t票，生成一个
        if (t==null){

            t= TicketUtils.next(user.getId());
            ticketService.addTicket(t);
            return t.getTicket();
        }
        //是否过期
        if(t.getExpired_at().before(new Date())){
            //删除
            ticketService.deleteTicket(t.getId());
            t = TicketUtils.next(user.getId());
            ticketService.addTicket(t);
            return t.getTicket();
        }




        return t.getTicket();

    }
    /**
     * 用户退出登录，只需要删除数据库中用户的t票
     * @param t
     */
    public void logout(String t){
        ticketService.deleteTicket(t);
    }

    /**
     * 注册一个用户，并返回用户t票
     *
     * @return 用户当前的t票
     */
    public String register(User user) throws Exception {

        //信息检查
        if (userService.getUser(user.getEmail()) != null) {
            throw new LoginRegisterException("用户邮箱已经存在！");
        }

        //密码加密
        String p=passwordEncoder.encode(user.getPassword());
        user.setPassword(p);
        //数据库添加用户
        userService.addUser(user);

        //生成用户t票
        Ticket ticket = TicketUtils.next(user.getId());
        //数据库添加t票
        ticketService.addTicket(ticket);

        return ticket.getTicket();

    }
}
