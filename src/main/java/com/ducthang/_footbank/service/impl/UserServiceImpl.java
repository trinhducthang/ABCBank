package com.ducthang._footbank.service.impl;

import com.ducthang._footbank.dto.UserDTO;
import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.entity.enum_.Gender;
import com.ducthang._footbank.entity.enum_.Role;
import com.ducthang._footbank.mapper.UserMapper;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createUser.setRole(Role.USER);
        user.setPassword("**************");
        userRepository.save(createUser);
        return user;
    }

    public List<UserDTO> create100Users() {
        List<UserDTO> userDTOList = new ArrayList<>();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        for (int i = 0; i < 100; i++) {
            UserDTO userDTO = new UserDTO();

            // Tạo dữ liệu giả cho các trường trong UserDTO
            userDTO.setUsername("user" + (i + 1));  // Tạo username ngẫu nhiên: user1, user2, ...
            userDTO.setPassword("thang");  // Mật khẩu mặc định
            userDTO.setFirstName("FirstName" + (i + 1));  // Tên giả
            userDTO.setLastName("LastName" + (i + 1));  // Họ giả
            userDTO.setEmail("user" + (i + 1) + "@example.com");  // Email giả
            userDTO.setPhone("123456789" + i);  // Số điện thoại giả
            userDTO.setDob(new java.util.Date());  // Ngày sinh giả
            userDTO.setGender(i % 2 == 0 ? Gender.MALE : Gender.FEMALE);  // Giới tính giả (nam/nữ luân phiên)
            userDTO.setAddress("Address " + (i + 1));  // Địa chỉ giả

            // Mã hóa mật khẩu
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // Mã hóa mật khẩu "thang"

            // Kiểm tra trùng lặp (nếu có)
            if (checkOverlap(userMapper.toEntity(userDTO))) {
                throw new RuntimeException("username already exists");
            }

            // Chuyển từ UserDTO sang User entity
            User user = userMapper.toEntity(userDTO);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setRole(Role.USER);  // Giả sử mặc định role là CLIENT

            // Lưu người dùng vào CSDL
            userRepository.save(user);

            // Ẩn mật khẩu trong UserDTO trước khi trả về
            userDTO.setPassword("**************");

            // Thêm UserDTO vào danh sách trả về
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public String getFullName(String username) {
        User user = userRepository.getUserByUsername(username);
        return user.getLastName() + " " + user.getFirstName();
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not exits"));
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserDTO getUser(Long id) {
        UserDTO userDTO = userMapper.toDto(userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not exits")));
        userDTO.setPassword(null);
        return userDTO;
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
    public UserDTO updateUser( Long id,UserDTO user) {
        User user1 = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not exits"));
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setDob(user.getDob());
        user1.setGender(user.getGender());
        user1.setAddress(user.getAddress());
        user1.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user1);
        return user;
    }

    public List<Long> getUserRegistrationCountByMonthAndDay(int year, int month) {
        // Tạo danh sách các ngày trong tháng
        List<Long> userCountsPerDay = new ArrayList<>();
        LocalDate startOfMonth = LocalDate.of(year, month, 1);  // Ngày đầu tiên của tháng
        int lengthOfMonth = startOfMonth.lengthOfMonth();  // Số ngày trong tháng

        // Lặp qua từng ngày trong tháng và lấy số lượng người dùng đăng ký
        for (int day = 1; day <= lengthOfMonth; day++) {
            LocalDateTime startOfDay = LocalDate.of(year, month, day).atStartOfDay();
            LocalDateTime endOfDay = LocalDate.of(year, month, day).atTime(23, 59, 59);
            Long count = userRepository.countUsersByCreatedAtRange(startOfDay, endOfDay);
            userCountsPerDay.add(count);
        }
        return userCountsPerDay;
    }


    public boolean checkOverlap(User user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }



}
