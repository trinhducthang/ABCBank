package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.dto.UserDTO;
import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.mapper.UserMapper;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO user) {
        if (checkOverlap(userMapper.toEntity(user))) {
            throw new RuntimeException("username already exists");
        }
        User createUser = userMapper.toEntity(user);
        createUser.setCreatedAt(LocalDateTime.now());
        createUser.setUpdatedAt(LocalDateTime.now());
        userRepository.save(createUser);
        return user;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    public boolean checkOverlap(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }
}
