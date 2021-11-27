package com.example.shopback.controllers;

import com.example.shopback.models.User;
import com.example.shopback.services.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
        userService.createUser(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user){
        user.setRole("USER");
        userService.createUser(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            params = {"id"})
    public void deleteUserById(
            @RequestParam(value = "id") Integer id){
        userService.deleteUserById(id);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET,
            params = {"id"})
    public Optional<User> findUser(
            @RequestParam(value = "id") Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Optional<User> checkUser(@RequestBody String data) {
        JSONObject dataJson = new JSONObject(data);
        String login = dataJson.getString("login");
        String password = dataJson.getString("password");
        return userService.findUserByLoginPassword(login, password);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }
}
