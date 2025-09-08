package com.order_mgr.order_service.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String startup() {
        return "<a href='/login'>Login with GitHub</a>";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
            model.addAttribute("login", principal.getAttribute("login"));
            model.addAttribute("avatar_url", principal.getAttribute("avatar_url"));
        }
        return "home"; // Renders home.html
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("user", principal);
        return "profile"; // Renders profile.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Renders login.html
    }
}


