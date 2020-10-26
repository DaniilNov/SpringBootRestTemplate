package com.example.resttemplate.controller;

import com.example.resttemplate.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    private static String GET_ALL = "http://91.241.64.178:7081/api/users";
    private static String POST_USER = "http://91.241.64.178:7081/api/users";
    private static String PUT_USER = "http://91.241.64.178:7081/api/users";
    private static String DELETE_USER = "http://91.241.64.178:7081/api/users/{id}";

    static RestTemplate restTemplate = new RestTemplate();
    private static String Post;
    private static String Put;
    private static String Del;


    private static String Cookie;

    public static void main(String[] args) {


        getAllUsers();


    }

    private static void getAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("param", httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(GET_ALL, HttpMethod.GET, entity, String.class);
        HttpHeaders headers = result.getHeaders();
        Cookie = headers.getFirst(headers.SET_COOKIE);
        System.out.println(result.toString());
        System.out.println(Cookie);
        postUser();
        putUser();
        deleteUser();
        System.out.println(Post + Put + Del);


    }

    private static void postUser() {

        User user = new User(3L, "James", "Brown", (byte) 20);

        RequestEntity<User> requestEntity = RequestEntity
                .post(URI.create(POST_USER))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Cookie", Cookie)
                .body(user);
        ResponseEntity<String> entity = restTemplate.exchange(requestEntity, String.class);
        Post = entity.getBody();

        System.out.println(entity);
    }


    private static void putUser() {
        Long id = 3L;
        User updateUser = new User(id, "Thomas", "Shelby", (byte) 30);
        RequestEntity<User> requestEntity = RequestEntity
                .put(URI.create(PUT_USER))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Cookie", Cookie)
                .body(updateUser);
        ResponseEntity<String> entity = restTemplate.exchange(requestEntity, String.class);
        Put = entity.getBody();

        System.out.println(entity);
    }

    private static void deleteUser() {
        Map<String, Long> param = new HashMap<>();
        param.put("id", 3L);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", Cookie);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(DELETE_USER, HttpMethod.DELETE, httpEntity, String.class, param);
        Del = responseEntity.getBody();
        System.out.println(responseEntity);
    }

}






