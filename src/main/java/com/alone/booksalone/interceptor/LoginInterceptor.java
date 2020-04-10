package com.alone.booksalone.interceptor;

import com.alone.booksalone.model.Ticket;
import com.alone.booksalone.model.User;
import com.alone.booksalone.service.TicketService;
import com.alone.booksalone.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.util.resources.cldr.kea.TimeZoneNames_kea;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * 拦截器实现登录管理
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TicketService ticketService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Ticket tickets=null;
        //从cookie中获取ticket
        String ticket= CookieUtils.getCookie("t",request);
        if(ticket!=null) {
             tickets = ticketService.getTicket(ticket);
            if (tickets==null) {
                response.sendRedirect("/users/login");
                return false;
            }
        }else {
            response.sendRedirect("/users/login");
            return false;
        }


        //过期t票，去登陆
        if(tickets.getExpired_at().before(new Date())){
            logger.info("过期");

            response.sendRedirect("/users/login");
            return false;
        }

        return true;
    }
}
