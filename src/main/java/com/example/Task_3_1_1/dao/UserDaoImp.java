package com.example.Task_3_1_1.dao;


import com.example.Task_3_1_1.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void modify(User user) {
        entityManager.merge(user);
    }

    @Override
    public void remove(User user) {
        User userForRemove = findUserById(user.getId());
        entityManager.remove(userForRemove);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User u").getResultList();
    }

    @Override
    public User findUserById(Long id) {

        return entityManager.find(User.class, id);
    }
}
