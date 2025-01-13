package com.jshans.springbootwebclient.controller.user;

import com.jshans.springbootwebclient.dto.user.UserResponseDto;
import com.jshans.springbootwebclient.dto.user.UserSaveRequestDto;
import com.jshans.springbootwebclient.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${token}")
    private String serverToken;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestHeader(name = "server-token") String serverToken,
                                                @RequestBody UserSaveRequestDto userSaveRequestDto) {
        validateToken(serverToken);
        return new ResponseEntity<>(userService.save(userSaveRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@RequestHeader(name = "server-token") String serverToken,
                                                    @PathVariable(name = "id") long id) {
        validateToken(serverToken);

        UserResponseDto userDto = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not exists user"));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(@RequestHeader(name = "server-token") String serverToken) {
        validateToken(serverToken);

        List<UserResponseDto> userDtos = userService.findAll();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    private void validateToken(String serverToken) {
        if (!serverToken.equals(this.serverToken)) {
            throw new IllegalArgumentException("Incorrect server token");
        }
    }
}
