package com.example.Task_3_1_1.controller;

import com.example.Task_3_1_1.model.User;
import com.example.Task_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    List<User> addIfEmpty = new ArrayList<>(Arrays.asList(
            new User("Ivan", "Sidorov","ivan@mail.ru"),
            new User("Oleg", "Semyonov","oleg@gmail.ru"),
            new User("Dmitriy", "Petrov","dimasik@gmail.com"),
            new User("Denis", "Komarov","den273@mail.ru"),
            new User("Sergey", "Fedorov","serega374@gmail.com")
    ));


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value="/user")
    public String getUserForm(Model model) {

        List<User> listUsers = userService.listUsers();
        if (listUsers.size() == 0){
            addIfEmpty.stream().forEach(t-> userService.add(t));
            listUsers = List.copyOf(addIfEmpty);
        }
        model.addAttribute("UserTitle", "User Controller page");
        model.addAttribute("ListOfUsers", listUsers);
        return "user";
    }

    @GetMapping(value="/user_create")
    public String createUser(Map<String, Object> model){
        User newUser = new User();
        model.put("newuser", newUser);
        return"user_create";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("newuser") User newuser){
        userService.add(newuser);
        return "redirect:/user";
    }

    @GetMapping(value="/modify")
    public String modifyUser(@RequestParam Long id, Map<String, Object> model){
        User userForMod = userService.findUserById(id);
        model.put("userForMod", userForMod);
        return "user_modify";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userForMod") User user){

        userService.modify(user);
        return "redirect:/user";
    }

    @GetMapping(value="/delete")
    public String deleteUser(@RequestParam Long id){
        User userForDel = userService.findUserById(id);
        userService.remove(userForDel);
        return "redirect:/user";
    }
}
