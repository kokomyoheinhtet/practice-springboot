package com.koko.practicespringboot.service;

import com.koko.practicespringboot.entity.User;
import com.koko.practicespringboot.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);
}
