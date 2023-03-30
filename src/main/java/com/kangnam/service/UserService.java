package com.kangnam.service;

import com.kangnam.dto.UserDTO;
import com.kangnam.persistence.UserRepository;
import com.kangnam.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createMember(String email, String password) {
        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .password(password)
                .build();
        UserEntity registeredUser = create(userEntity);
        return UserDTO.entityToDTO(registeredUser);
    }

    private UserEntity create(UserEntity userEntity) {
        validate(userEntity);
        return userRepository.save(userEntity);
    }

    private void validate(UserEntity userEntity) {
        if (userEntity == null)
            throw new IllegalArgumentException("Entity is empty.");
        else if (userEntity.getEmail() == null)
            throw new IllegalArgumentException("Email is null.");
        else if (userRepository.existsByEmail(userEntity.getEmail()))
            throw new IllegalArgumentException("Email already exists.");

    }

    public UserEntity getByCredentials(final String email, final String password) {
        final UserEntity origin = userRepository.findByEmail(email);
        if (origin != null && origin.getPassword().equals(password))
            return origin;
        return null;
    }
}
