package com.boot.service;

import com.boot.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<String> getPermissionsByUsername(String username);
    List<String> getRoleNameByUsername(String username);
}
