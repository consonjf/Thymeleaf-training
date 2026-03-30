package com.user_role.thymeleaf_demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AuthController {

    private static final Map<String, String> USERS = Map.of(
        "admin", "admin123",
            "jeff", "pass123",
            "user", "user123"
    );

    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @PostMapping("/login")
    public String attemptLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model)
    {
        String storedpassword = USERS.get(username);

        if(storedpassword == null || !storedpassword.equals(password))
        {
        model.addAttribute("error", "Invalid username or password. Please try again.");
        return "Login";
        }

        String role = username.equalsIgnoreCase("admin") ? "ADMIN" : "USER";

        session.setAttribute("username", username);
        session.setAttribute("role", role);

        System.out.println("LOGIN sessionId=" + session.getId());

        return "redirect:/loading";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String sessionId = session.getId();
        String username = (String) session.getAttribute("username");

        session.invalidate();

        System.out.println("LOGOUT: Session cleared. sessionId=" + sessionId + ", username=" + username);

        return "redirect:/login";
    }
}