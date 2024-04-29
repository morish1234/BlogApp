package com.blogApp.payload;

import lombok.Data;

@Data
public class SingUp {
    private  long id;
    private String name;
    private  String email;
    private String username;
    private String password;
}
