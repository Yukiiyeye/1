package com.yyx.assetsys.service.impl;

import com.yyx.assetsys.mapper.UserMapper;
import com.yyx.assetsys.entity.User;
import com.yyx.assetsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void registerUser(User user) {
        userMapper.registerUser(user);
    }
    @Override
    public User loginUser(User user) {
        User u = userMapper.findUserByName(user.getUsername());
        if (u != null && u.getPassword().equals(user.getPassword())) {
            return u;
        } else {
            return null;
        }

    }
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }







}

