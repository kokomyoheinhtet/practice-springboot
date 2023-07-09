package com.koko.practicespringboot.controller;

import com.koko.practicespringboot.entity.User;
import com.koko.practicespringboot.entity.VerificationToken;
import com.koko.practicespringboot.event.RegistrationCompleteEvent;
import com.koko.practicespringboot.model.UserModel;
import com.koko.practicespringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
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

    @GetMapping("/verifyRegistration")
    public ResponseEntity<String> verifyRegistration(@RequestParam String token) {
        String result = userService.validateVerificationToken(token);
        if (StringUtils.equalsIgnoreCase("valid", result)) {
            return ResponseEntity.ok("User verification success");
        }

        return ResponseEntity.badRequest().body("token is invalid");
    }

    @GetMapping("/resendVerifyToken")
    public ResponseEntity<String> resendVerifyToken(@RequestParam String token, HttpServletRequest request) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(token);

        resendVerificationTokenMail(verificationToken, request);
        return ResponseEntity.ok("New link sent");
    }

    private void resendVerificationTokenMail(VerificationToken verificationToken, HttpServletRequest request) {
        // Send email to user
        String url = applicationUrl(request) + "verifyRegistration?token=" + verificationToken.getToken();
        log.info("Click the link to verify your account: {}", url);
    }
}
