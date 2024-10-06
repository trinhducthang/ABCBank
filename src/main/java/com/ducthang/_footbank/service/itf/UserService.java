package com.ducthang._footbank.service.itf;

import com.ducthang._footbank.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDTO createUser(UserDTO user);
    public boolean deleteUser(Long id);
    public UserDTO getUser(Long id);
    public List<UserDTO> getUsers();
    public UserDTO updateUser(UserDTO user);
}
