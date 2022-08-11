package com.graduation.authentication.controller;

import com.graduation.authentication.model.Login;
import com.graduation.authentication.model.User;
import com.graduation.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")

public class UserController {

    @Autowired
    UserService userService;

    @Value("${server.port}")
    int port;
    @GetMapping("/port")
    public int getPort(){
        //System.out.println(port);
        return port;
    }
    @PostMapping("/addStudent")
    public User addStudent(String username,String password,String email){
        return userService.addStudent(username,password,email);
    }

    @PostMapping("/changePassword")
    public User changeMyPassword(Principal principal, String password){
        return userService.changeMyPassword(principal,password);
    }

    @GetMapping("/myinfo")
    public Principal getMyInfo(Principal principal)
    {
        return principal;
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String,String>> login(@RequestBody Login login2){
        String username=login2.getUsername(),password=login2.getPassword();
        System.out.println(username+"\n"+password);
        HashMap<String, String> login=userService.login(username,password);
        System.out.println(login.toString());
       //// response.getHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        return ResponseEntity.ok(login);
    }


}
