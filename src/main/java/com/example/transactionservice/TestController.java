package com.example.transactionservice;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/ping")
    public String ping(HttpServletRequest request) {
        var userId = request.getHeader("x-user-id");
        return "pong";
    }
}
