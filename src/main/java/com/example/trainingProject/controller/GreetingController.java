package com.example.trainingProject.controller;

import com.example.trainingProject.config.MyUserDetails;
import com.example.trainingProject.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal MyUserDetails user, Model model) {
        if(user == null){
            model.addAttribute("username","user");
        }
        else {
            model.addAttribute("username",user.getUsername());
        }
        return "greeting";
    }
}
