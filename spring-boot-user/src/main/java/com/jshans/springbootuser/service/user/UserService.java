package com.jshans.springbootuser.service.user;

import com.jshans.springbootuser.dto.user.UserResponseDto;
import com.jshans.springbootuser.dto.user.UserSaveRequestDto;
import com.jshans.springbootuser.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final List<User> users = new ArrayList<>();

    public UserResponseDto save(UserSaveRequestDto userSaveRequestDto) {
        User user = new User(
                userSaveRequestDto.getId(),
                userSaveRequestDto.getName(),
                userSaveRequestDto.getPassword(),
                userSaveRequestDto.getEmail(),
                LocalDateTime.now()
        );
        users.add(user);
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreateDateTime());
    }
    public List<UserResponseDto> findAll() {
        return users.stream()
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreateDateTime()))
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDto> findById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreateDateTime()))
                .findFirst();
    }

}
