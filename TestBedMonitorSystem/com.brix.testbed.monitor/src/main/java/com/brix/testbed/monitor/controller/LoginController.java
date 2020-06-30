package com.brix.testbed.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error, 
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
		String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect.";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out.";
        }
        model.addAttribute("errorMessge", errorMessge);
		return "login";
	}
	
	@RequestMapping(value = { "/log-out" }, method = RequestMethod.GET)
	public String logout() {
		return "logout";
	}
}
