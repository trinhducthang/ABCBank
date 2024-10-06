package com.ducthang._footbank.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

//request nen dua vao package dto
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    String username;
    String password;
}