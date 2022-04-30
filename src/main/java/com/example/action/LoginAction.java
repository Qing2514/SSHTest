package com.example.action;

import com.example.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAction {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String execute(String username, String password) {
        if (userService.loginVerify(username, password)) {
            return "login success";
        } else {
            return "login fail";
        }
    }
}
