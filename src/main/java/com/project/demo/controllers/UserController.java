package com.project.demo.controllers;

import com.project.demo.dao.UserDao;
import com.project.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.project.demo.models.User.UserBuilder.anUser;

@RestController
public class UserController {

    //makes the userDaoImp class create an object and stores it in this variable,
    // if use in others parts de proyect this object will be shared in memory
    // so that not too many of these are created.
    //DEPENDENCY INJECTION
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){

        User user = anUser()
                .witId(id)
                .withName("sole")
                .withLastName("tancredi")
                .withEmail("tancredi620@gmail.com")
                .withTelephone("1168083141")
                .withPassword("1234").build();

        return user;
    }

    @RequestMapping(value = "api/users")
    public List<User> getUsers(){
        return userDao.getUsers();
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        userDao.delete(id);

    }
}
