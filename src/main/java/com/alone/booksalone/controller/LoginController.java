package com.alone.booksalone.controller;

import com.alone.booksalone.model.User;
import com.alone.booksalone.service.LoginService;
import com.alone.booksalone.service.UserService;
import com.alone.booksalone.util.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @RequestMapping(path = {"/users/register"}, method = {RequestMethod.GET})
    public String register() {
        return "login/register";
    }

    @RequestMapping(path = {"/users/register/do"}, method = {RequestMethod.POST})
    public String doRegister(
            Model model,
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        try {
            String t = loginService.register(user);
            CookieUtils.writeCookie("t", t, response);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "404";
        }
    }

    @RequestMapping(path = {"/users/login"}, method = {RequestMethod.GET})
    public String login() {
        return "login/login";
    }

    @RequestMapping(path = {"/users/login/do"}, method = {RequestMethod.POST})
    public String doLogin(
            Model model,
            HttpServletResponse response,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        try {
            String t = loginService.login(email, password);
            CookieUtils.writeCookie("t", t, response);

            
            return "redirect:/index";
        } catch (Exception e) {
            logger.info("error:"+e.getMessage()+e);
            model.addAttribute("error", e.getMessage());
            return "404";
        }
    }

    @RequestMapping(path = {"/users/logout/do"}, method = {RequestMethod.GET})
    public String doLogout(
            @CookieValue("t") String t,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        CookieUtils.removeCookie("t",request,response);
        loginService.logout(t);
        return "redirect:/index";

    }
}
