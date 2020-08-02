package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {
    Integer userid;
    String username;
    String salt;
    String password;
    String firstName;
    String lastName;

    public User(Integer userid, String username, String salt, String password, String firstName, String lastName) {
        this.userid = userid;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getPassword(){
        return this.password;
    }
    public String getSalt(){
        return this.salt;
    }
    public String getUsername(){
        return this.username;
    }
    public String getFirstname()
    {
        return this.firstName;
    }
    public String getLastname(){
        return this.lastName;
    }
    public Integer getUserId(){
        return this.userid;
    }
}
