package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.dto.UserDTO;
import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.mapper.UserMapper;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not exits"));
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserDTO getUser(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not exits")));
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.toDto(user));
        }
        return userDTOs;
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        User user1 = userRepository.findById(user.getId()).orElseThrow(()-> new RuntimeException("user not exits"));
        user1 = userMapper.toEntity(user);
        user1.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user1);
        return user;
    }

    public boolean checkOverlap(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }
}
