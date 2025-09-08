package com.order_mgr.order_service.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public String startup() {
        return "<a href='/login'>Login with GitHub</a>";
    }

    @GetMapping("/home")
    public String home() {
        return """
            <html>
                <body>
                    <h1>Welcome! You are logged in.</h1>
                    <a href="/logout">Logout</a>
                </body>
            </html>
        """;
    }

    @GetMapping("/profile")
    public Map<String, Object> getUser(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes(); // returns GitHub user info
    }
}

