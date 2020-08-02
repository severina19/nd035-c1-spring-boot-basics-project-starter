package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView(){
        return "signup";
    }
    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model){
        String signupError = null;
        if (this.userService.usernameAvailable(user.getUsername()) == false){
            signupError = "The user name already exists";
        }
        if (signupError == null){
            int rowsAdded = this.userService.createUser(user);
            if (rowsAdded < 0){
                signupError = "An error occured during sign up, try again later";
            }
        }
        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }


}
