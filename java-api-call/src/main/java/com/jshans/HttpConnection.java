package com.jshans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jshans.request.ApiRequest;
import com.jshans.request.ApiURL;
import com.jshans.request.user.UserSaveRequest;
import com.jshans.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpConnection {
    public static void main(String[] args) throws JsonProcessingException {
        ApiRequest userSaveRequest = new UserSaveRequest(1L, "name", "password", "email@email.com");

        Map<String, String> headers = new HashMap<>();
        headers.put("server-token", "test-token");
        String response = requestPost(userSaveRequest, ApiURL.CREATE_USER_REQUEST, headers);
        ObjectMapper objectMapper = createObjectMapper();

        User user = objectMapper.readValue(response, User.class);
        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.getEmail() = " + user.getEmail());
    }

    public static String requestPost(ApiRequest apiRequest, ApiURL apiURL, Map<String, String> headers) {
        try {
            URL url = new URL(apiURL.getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(HttpMethod.POST.toString());
            connection.setRequestProperty("Content-Type", "application/json");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            connection.setDoOutput(true);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(apiRequest.toJsonString());
            outputStreamWriter.flush();

            int responseCode = connection.getResponseCode();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))
            ) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
