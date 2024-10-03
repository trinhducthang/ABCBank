package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (checkOverlap(user, userRepository.findByUsername(user.getUsername()))) {
            throw new RuntimeException("username already exists");
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    public boolean checkOverlap(User user1, User user2) {
        return !user1.getUsername().equals(user2.getUsername());
    }
}
