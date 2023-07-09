package com.koko.practicespringboot.service;

import com.koko.practicespringboot.entity.User;
import com.koko.practicespringboot.entity.VerificationToken;
import com.koko.practicespringboot.model.UserModel;
import com.koko.practicespringboot.repository.UserRepository;
import com.koko.practicespringboot.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;


    @Override
    public User registerUser(UserModel userModel) {
        User user = User.builder()
                .email(userModel.getEmail())
                .name(userModel.getName())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .role("USER").build();

        return userRepository.save(user);
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);

        verificationTokenRepository.save(verificationToken);
    }
}
