package com.ducthang._footbank.controller;

import com.ducthang._footbank.dto.UserDTO;
import com.ducthang._footbank.dto.response.ApiResponse;
import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.repository.UserRepository;
import com.ducthang._footbank.service.itf.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/create")
    public ApiResponse<UserDTO> createUser(@Valid @RequestBody UserDTO user){
        System.out.println("createUser called with: " + user);

        try {
            return ApiResponse.<UserDTO>builder()
                    .code(HttpStatus.OK.value())
                    .message("Create user success!")
                    .result(userService.createUser(user))
                    .build();
        }
        catch (Exception e) {
            return ApiResponse.<UserDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @GetMapping("/info")
    public ApiResponse<UserDTO> getUser(@RequestParam long id){
        try {
            return ApiResponse.<UserDTO>builder()
                    .code(HttpStatus.OK.value())
                    .message("Get user success!")
                    .result(userService.getUser(id))
                    .build();
        }
        catch (Exception e) {
            return ApiResponse.<UserDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
        }
    }


    @DeleteMapping("/info")
    public ApiResponse<?> deleteUser(@RequestParam long id){
        try{
            return new ApiResponse<>(HttpStatus.OK.value(), "Delete success",userService.deleteUser(id));
        }
        catch (Exception e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Delete failed because "+ e.getMessage(), null);
        }
    }

    @PutMapping("/info/{id}")
    public ApiResponse<UserDTO> updateUser(@Valid @PathVariable Long id, @RequestBody UserDTO user){
        try{
            return ApiResponse.<UserDTO>builder()
                    .code(HttpStatus.OK.value())
                    .message("Update user success!")
                    .result(userService.updateUser(id,user))
                    .build();
        }
        catch (Exception e) {
            return ApiResponse.<UserDTO>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<UserDTO>> getAllUsers(){
        return ApiResponse.<List<UserDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all users success!")
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/generate-users")
    public List<UserDTO> generateUsers() {
        return userService.create100Users();
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/user-registration/by-day")
    public ApiResponse<List<Long>> getUserRegistrationCountByDayOfMonth(
            @RequestParam int year, @RequestParam int month) {
        List<Long> dailyUserCounts = userService.getUserRegistrationCountByMonthAndDay(year, month);
        return ApiResponse.<List<Long>>builder()
                .code(HttpStatus.OK.value())
                .message("Get daily registration count for the specified month success!")
                .result(dailyUserCounts)
                .build();
    }

    @GetMapping("/getname")
    public String getFullName(@RequestParam String userName) {
        return userService.getFullName(userName);
    }

    @GetMapping("/get-by-username")
    public User getByUserName(@RequestParam String username){
        return userService.getUserByUserName(username);
    }


    @PutMapping("/updatePassword/{username}")
    public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestParam String password) {
        try{
            userService.updatePassword(username, password);
            return ResponseEntity.ok("Password updated successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/forgotPassword/{username}")
    public String forgotPassword(@PathVariable String username) {
        try{
            return userService.getEmailByUsername(username);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
}
