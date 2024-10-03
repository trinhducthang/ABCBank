package com.ducthang._footbank.controller;

import com.ducthang._footbank.entity.User;
import com.ducthang._footbank.response.ApiResponse;
import com.ducthang._footbank.service.itf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ApiResponse<User> createUser(@RequestBody User user){
        try {
            return ApiResponse.<User>builder()
                    .code(HttpStatus.OK.value())
                    .message("Create user success!")
                    .result(userService.createUser(user))
                    .build();
        }
        catch (Exception e) {
            return ApiResponse.<User>builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .result(null)
                    .build();
        }
    }

}
