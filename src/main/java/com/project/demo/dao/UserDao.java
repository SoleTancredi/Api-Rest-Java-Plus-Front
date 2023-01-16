package com.project.demo.dao;

import com.project.demo.models.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void delete(Long id);

    void register(User user);
    User getUserByCredentials(User user);
}
