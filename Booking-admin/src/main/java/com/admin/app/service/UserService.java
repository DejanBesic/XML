package com.admin.app.service;

import com.admin.app.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    User findByEmail(String email);
}
