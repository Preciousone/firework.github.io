package com.lr.controller;

import com.lr.dao.UserDao;
import com.lr.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/front/*")
public class IndexController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/addregister")
    public String register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password.equals(password2)){
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userDao.save(userEntity);
            return "login";
        }else {
            return "register";
        }
    }

    @RequestMapping("/addlogin")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserEntity userEntity = userDao.findByUsernameAndPassword(username,password);
        String str = "";
        if (userEntity !=null){
            str = "index";
        }else {
            str = "login";
        }
        return str;
    }

}
