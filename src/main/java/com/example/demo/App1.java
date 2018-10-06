package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class App1 {
	
    @RequestMapping("/api/ume")
    public String get() {
        return "ume\n";
    }
}
