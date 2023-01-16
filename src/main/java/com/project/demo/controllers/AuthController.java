package com.project.demo.controllers;

import com.project.demo.dao.UserDao;
import com.project.demo.models.User;
import com.project.demo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user){

        User userlogueado = userDao.getUserByCredentials(user);

        if(userlogueado != null){

            return jwtUtils.create(String.valueOf(userlogueado.getId()), userlogueado.getEmail());
        }else{
            return "FAil";
        }
    }
}
