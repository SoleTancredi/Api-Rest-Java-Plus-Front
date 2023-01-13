package com.project.demo.controllers;

import com.project.demo.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.project.demo.models.User.UserBuilder.anUser;

@RestController
public class UserController {

    @RequestMapping(value = "user/{id}")
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

    @RequestMapping(value = "users")
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();

        User user1 = anUser()
                .witId(12L)
                .withName("sole")
                .withLastName("tancredi")
                .withEmail("tancredi620@gmail.com")
                .withTelephone("1168083141")
                .withPassword("1234").build();

        User user2 = anUser()
                .witId(21L)
                .withName("martin")
                .withLastName("maldonado")
                .withEmail("maldonado@gmail.com")
                .withTelephone("11111111")
                .withPassword("5555555").build();

        User user3 = anUser()
                .witId(31L)
                .withName("esther")
                .withLastName("alvarez")
                .withEmail("ealv@gmail.com")
                .withTelephone("17854296")
                .withPassword("12356468").build();

        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }
}
