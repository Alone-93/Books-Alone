package com.alone.booksalone.config;

import com.alone.booksalone.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class BookWebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;//拦截器一定要使用注入，使用new创建对象时会导致拦截器中的依赖注入为null
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration loginRegistry=registry.addInterceptor(loginInterceptor);
        //拦截路径
        loginRegistry.addPathPatterns("/**");
        //排除路径
        loginRegistry.excludePathPatterns("/users/login");
        loginRegistry.excludePathPatterns("/users/login/do");
        loginRegistry.excludePathPatterns("/users/register");
        loginRegistry.excludePathPatterns("/users/register/do");

    }

}
