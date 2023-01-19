package com.vega.springit.service;

import com.vega.springit.domain.User;
import com.vega.springit.repository.UserRepository;
import jdk.nashorn.internal.ir.Optimistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User register(User user) {
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void saveUsers(User... users) {
        for (User user : users) {
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

            logger.info("Saving User: " + user.getEmail());
            userRepository.save(user);
        }
    }


}
