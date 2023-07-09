package com.koko.practicespringboot.event.listener;

import com.koko.practicespringboot.entity.User;
import com.koko.practicespringboot.event.RegistrationCompleteEvent;
import com.koko.practicespringboot.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@AllArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create the veri token for the user
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);

        // Send email to user
        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;
        log.info("Click the link to verify your account: {}", url);
    }
}
