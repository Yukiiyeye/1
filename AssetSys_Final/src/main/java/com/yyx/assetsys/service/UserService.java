package com.yyx.assetsys.service;

import com.yyx.assetsys.entity.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    User loginUser(User user);
    User findUserByName(String username);

    List<User> findAllUser();

    void updateUser(User user);
}

