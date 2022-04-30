package com.example.action;

import com.example.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterAction {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String execute(String username, String password) {
		if (!userService.loginVerify(username, password)) {
			return "register success";
		} else {
			return "register fail";
		}
	}
}
