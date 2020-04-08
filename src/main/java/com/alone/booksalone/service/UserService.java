package com.alone.booksalone.service;

import com.alone.booksalone.dao.UserDAO;
import com.alone.booksalone.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /**
     * 密码加密工具
     * @return
     */
    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private UserDAO userDAO;

    public int addUser(User user){
        return userDAO.addUser(user);
    }

    public User getUser(String email) {
        return userDAO.selectByEmail(email);
    }

    public User getUser(int uid) {
        return userDAO.selectById(uid);
    }
}
