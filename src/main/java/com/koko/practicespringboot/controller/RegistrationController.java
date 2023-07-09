package com.koko.practicespringboot.controller;

import com.koko.practicespringboot.entity.User;
import com.koko.practicespringboot.event.RegistrationCompleteEvent;
import com.koko.practicespringboot.model.UserModel;
import com.koko.practicespringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel userModel,
                                               final HttpServletRequest request) {
        User user = userService.registerUser(userModel);

        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return ResponseEntity.ok(user.toString());
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
    }
}
