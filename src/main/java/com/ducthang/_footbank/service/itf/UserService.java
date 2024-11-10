package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.dto.UserDTO;
import com.ducthang._footbank.entity.User;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface UserService {
    public UserDTO createUser(UserDTO user);

//    @PostAuthorize("(returnObject.username == authentication.name) || hasRole('ROLE_ADMIN')")
    public boolean deleteUser(Long id);

    @PostAuthorize("(returnObject.username == authentication.name) || hasRole('ROLE_ADMIN')")
    public UserDTO getUser(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTO> getUsers();

    @PostAuthorize("returnObject.username == authentication.name")
    public UserDTO updateUser(Long id,UserDTO user);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Long> getUserRegistrationCountByMonthAndDay(int year, int month);

    public List<UserDTO> create100Users();

    public String getFullName(String username);

    public User getUserByUserName(String username);
}
