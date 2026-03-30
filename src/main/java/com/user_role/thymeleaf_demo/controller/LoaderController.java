package com.user_role.thymeleaf_demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoaderController {

    @GetMapping("loading")
    public String loading (HttpSession session){
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if(username == null || role == null){
            return "redirect:/login";
        }
        return "Loading";
    }
}
