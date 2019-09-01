package com.shiro.service.user;

import com.shiro.domain.user.User;

public interface UserService {
    public User findByName(String name);
    public User findById(Integer id);
}
