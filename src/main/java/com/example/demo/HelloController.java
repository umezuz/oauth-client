package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Map<String, String> getMessage(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_USER")) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Hello, " + request.getRemoteUser() + "!!!");
            return response;
        }
        throw new UnauthorizedUserException("You don't have a required role. ");
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public void unauthorized(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }

}
