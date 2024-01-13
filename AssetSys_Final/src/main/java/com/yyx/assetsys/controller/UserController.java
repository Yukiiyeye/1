package com.yyx.assetsys.controller;

import com.yyx.assetsys.service.UserService;
import com.yyx.assetsys.entity.User;

import com.yyx.assetsys.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;
import com.yyx.assetsys.entity.Result;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByName")
    public User findUserByName(String username) {
        return userService.findUserByName(username);
    }


    @PostMapping("/register")
    public Result registerUser(@RequestBody User user) throws NoSuchAlgorithmException {
        String password = user.getPassword();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String hashedPassword = new String(hash, StandardCharsets.UTF_8);

        user.setPassword(hashedPassword);

        User u = userService.findUserByName(user.getUsername());
        if (u == null) {
            userService.registerUser(user);
            return Result.success();
        } else {
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result loginUser(@RequestBody User user) throws NoSuchAlgorithmException {
        String password = user.getPassword();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String hashedPassword = new String(hash, StandardCharsets.UTF_8);

        user.setPassword(hashedPassword);

        User u = userService.loginUser(user);
        if (u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            claims.put("role", u.getRole());

            // 生成JWT令牌
            String token = JwtUtil.genToken(claims);

            // 在成功的响应中返回令牌、id和username
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("id", u.getId());
            response.put("username", u.getUsername());
            response.put("role", u.getRole());

            return Result.success(response);

        } else {
            // 返回错误响应
            return Result.error("用户名或密码错误");
        }
    }
    @GetMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success().getMessage();

    }



}

