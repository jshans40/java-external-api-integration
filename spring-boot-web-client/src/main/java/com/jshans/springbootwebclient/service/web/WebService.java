package com.jshans.springbootwebclient.service.web;

import com.jshans.springbootwebclient.dto.user.UserResponseDto;
import com.jshans.springbootwebclient.dto.user.UserSaveRequestDto;
import com.jshans.springbootwebclient.model.api.ApiEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebService {

    private final WebClient.Builder webClientBuilder;

    public UserResponseDto requestCreateUser(UserSaveRequestDto userSaveRequestDto,
                                             String serverToken) {
        WebClient webClient = webClientBuilder.build();

        Mono<UserResponseDto> response = webClient.post()
                .uri(ApiEndpoint.USER_POST.getUri())
                .header("server-token", serverToken)
                .bodyValue(userSaveRequestDto)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new RuntimeException("4xx Client Error"))
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(new RuntimeException("5xx Server Error"))
                )
                .bodyToMono(UserResponseDto.class)
                .retry(3);

        return response.block();
    }

    public UserResponseDto findUserById(long id, String serverToken) {
        WebClient webClient = webClientBuilder.build();

        Mono<UserResponseDto> response = webClient.get()
                .uri(ApiEndpoint.USER_ONE_GET.getUri(), id)
                .header("server-token", serverToken)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new RuntimeException("4xx Client Error"))
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(new RuntimeException("5xx Server Error"))
                )
                .bodyToMono(UserResponseDto.class)
                .retry(3);


        return response.block();
    }

    public List<UserResponseDto> findAllUser(String serverToken) {
        WebClient webClient = webClientBuilder.build();

        Mono<List<UserResponseDto>> response = webClient.get()
                .uri(ApiEndpoint.USER_ALL_GET.getUri())
                .header("server-token", serverToken)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new RuntimeException("4xx Client Error"))
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(new RuntimeException("5xx Server Error"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<UserResponseDto>>() {})
                .retry(3);

        return response.block();
    }

    public Mono<UserResponseDto> requestAsyncCreateUser(UserSaveRequestDto userSaveRequestDto,
                                       String serverToken) {
        WebClient webClient = webClientBuilder.build();

        return webClient.post()
                .uri(ApiEndpoint.USER_POST.getUri())
                .header("server-token", serverToken)
                .bodyValue(userSaveRequestDto)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new RuntimeException("4xx Client Error"))
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(new RuntimeException("5xx Server Error"))
                )
                .bodyToMono(UserResponseDto.class)
                .retry(3);
    }



}
