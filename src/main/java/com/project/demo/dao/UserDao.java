package com.project.demo.dao;

import com.project.demo.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void delete(Long id);
}
