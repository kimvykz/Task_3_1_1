package com.example.Task_3_1_1.service;

import com.example.Task_3_1_1.dao.UserDao;
import com.example.Task_3_1_1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {

        if (user.getFirstName() != "" &&
            user.getLastName() != "" &&
            user.getEmail() != "") {
            userDao.add(user);
        }
    }

    @Transactional
    @Override
    public void modify(User user) {
        if (user.getFirstName() != "" &&
                user.getLastName() != "" &&
                user.getEmail() != "") {
            userDao.modify(user);
        }
    }

    @Transactional
    @Override
    public void remove(User user) {
        userDao.remove(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }
}
