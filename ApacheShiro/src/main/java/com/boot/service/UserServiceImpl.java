package com.boot.service;

import com.boot.mapper.UserMapper;
import com.boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public List<String> getPermissionsByUsername(String username) {
        return userMapper.getPermissionsByUsername(username);
    }

    @Override
    public List<String> getRoleNameByUsername(String username) {
        return userMapper.getRoleNameByUsername(username);
    }
}
