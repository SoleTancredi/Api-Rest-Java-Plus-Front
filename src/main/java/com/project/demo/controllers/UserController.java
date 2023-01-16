package com.project.demo.controllers;

import com.project.demo.dao.UserDao;
import com.project.demo.models.User;
import com.project.demo.utils.JWTUtils;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private JWTUtils jwtUtils;

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

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,user.getPassword());
        User userPasswordHash = anUser()
                .witId(user.getId())
                .withName(user.getName())
                .withLastName(user.getLastName())
                .withEmail(user.getEmail())
                .withTelephone(user.getTelephone())
                .withPassword(hash).build();

        userDao.register(userPasswordHash);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){

        if(!validateToken(token)){
            return null;
        }


        return userDao.getUsers();
    }

    private boolean validateToken(String token){
        String userId = jwtUtils.getKey(token);
        return userId != null;
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if(!validateToken(token)){
            return;
        }
        userDao.delete(id);

    }
}
