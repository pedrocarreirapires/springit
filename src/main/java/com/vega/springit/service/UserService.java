package com.vega.springit.service;

import com.vega.springit.domain.Link;
import com.vega.springit.domain.Role;
import com.vega.springit.domain.User;
import com.vega.springit.domain.validator.PasswordsMatchValidator;
import com.vega.springit.repository.RoleRepository;
import com.vega.springit.repository.UserRepository;
import jdk.nashorn.internal.ir.Optimistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mailService = mailService;
        encoder = new BCryptPasswordEncoder();
    }

    public User register(User user) {

        //take the password from the form and encode
        String secret = "{bcrypt}" + encoder.encode(user.getPassword());
        user.setPassword(secret);
        //confirm the password
        user.setConfirmPassword(secret);
        //assign a role to this user
        user.addRole(roleRepository.findByName("ROLE_USER"));
        //set an activation code
        user.setActivationCode(UUID.randomUUID().toString());
        //user.setActivationCode();
        //disbale the user
        user.setEnabled(false);
        //save user
        save(user);
        //send the activation email
        sendActivationEmail(user);
        //return the user
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User>  findByEmailAndActivationCode(String email, String activationCode) {
        return userRepository.findByEmailAndActivationCode(email, activationCode);
    }

    @Transactional
    public void saveUsers(User... users) {
        for (User user : users) {
            logger.info("Saving User: " + user.getEmail());
            userRepository.save(user);
//            //Begin transaction
//            logger.info("Saving User: " + user.getEmail());
//            try {
//                userRepository.save(user);
//            } catch (Exception e){
//                //Rollback transaction
//            }
//
//
//Commit Transaction
//    }
        }
    }

    public void sendActivationEmail(User user){
       mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user){
        mailService.sendWelcomeEmail(user);
    }
}
