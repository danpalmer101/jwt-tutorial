package io.danpalmer101.jwt.generator.web;

public class UserResponse {

    private String name;

    public UserResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
