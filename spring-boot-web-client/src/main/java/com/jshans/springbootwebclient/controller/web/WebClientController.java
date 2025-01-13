package com.jshans.springbootwebclient.controller.web;

import com.jshans.springbootwebclient.dto.user.UserResponseDto;
import com.jshans.springbootwebclient.dto.user.UserSaveRequestDto;
import com.jshans.springbootwebclient.service.web.WebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/web-clients")
@RequiredArgsConstructor
@Slf4j
public class WebClientController {

    private final WebService webService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@RequestHeader(name = "server-token") String serverToken,
                                                      @RequestBody UserSaveRequestDto userSaveRequestDto) {
        return new ResponseEntity<>(webService.requestCreateUser(userSaveRequestDto, serverToken), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> findAllUser(@RequestHeader(name = "server-token") String serverToken) {
        return new ResponseEntity<>(webService.findAllUser(serverToken), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findAllUser(@RequestHeader(name = "server-token") String serverToken,
                                                       @PathVariable(name = "id") long userId) {
        return new ResponseEntity<>(webService.findUserById(userId, serverToken), HttpStatus.OK);
    }

    @PostMapping("/async-users")
    public ResponseEntity<UserResponseDto> createAsyncUser(@RequestHeader(name = "server-token") String serverToken,
                                                           @RequestBody UserSaveRequestDto userSaveRequestDto) {
        webService.requestAsyncCreateUser(userSaveRequestDto, serverToken)
                .doOnTerminate(() ->
                        log.info("create user complete")
                )
                .subscribe();

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
