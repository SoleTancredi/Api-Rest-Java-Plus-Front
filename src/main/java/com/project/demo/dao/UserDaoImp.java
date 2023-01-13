package com.project.demo.dao;

import com.project.demo.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

//this class will have the functionality to be able to access the database repository.
@Repository
//makes it possible for the class to build sql queries to the database.
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getUsers() {
        String query = "FROM User";
        //execute query
        return entityManager.createQuery(query).getResultList();
    }
}
