package com.ducthang._footbank.controller;

import com.ducthang._footbank.dto.UserDTO;
import com.ducthang._footbank.dto.response.ApiResponse;
import com.ducthang._footbank.service.itf.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

    @PutMapping("/info")
    public ApiResponse<UserDTO> updateUser(@Valid @RequestParam UserDTO user){
        try{
            return ApiResponse.<UserDTO>builder()
                    .code(HttpStatus.OK.value())
                    .message("Update user success!")
                    .result(userService.updateUser(user))
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



}
