package com.kangnam.dto;

import com.kangnam.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String email;
    private String password;

    public static UserDTO entityToDTO(UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }
}
