package com.musex.entities.User;


public enum Role {
    USER("User"),
    ADMIN("Admin");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


}

