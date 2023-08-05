package com.example.Task_3_1_1.service;



import com.example.Task_3_1_1.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void modify(User user);
    void remove(User user);

    List<User> listUsers();

    User findUserById(Long id);
}
