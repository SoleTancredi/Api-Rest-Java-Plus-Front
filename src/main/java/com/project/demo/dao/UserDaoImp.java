package com.project.demo.dao;

import com.project.demo.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void register(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByCredentials(User user) {
        // This prevents hacking by SQL injection.
        String query = "FROM User WHERE email = :email";
        List<User> list = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        if(list.isEmpty()){
            return null;
        }

        String passwordHashed = list.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, user.getPassword())){
            return list.get(0);
        }
        return null;
    }


}
