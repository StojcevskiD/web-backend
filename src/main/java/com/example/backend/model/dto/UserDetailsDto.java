package com.example.backend.model.dto;

import com.example.backend.model.User;
import com.example.backend.model.UserRoles;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailsDto {
    private String username;
    private List<UserRoles> role;

    public static UserDetailsDto of(User user){
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role=user.getRoles();
        return details;
    }
}
