package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Email;
import java.util.Date;
//import java.util.regex.Pattern;

public class User {

    //Regular expression used for email validation
    //private static Pattern regex = Pattern.compile("[\\w+?]@[\\w+?]+\\.\\w+?");
    private int userId;

    @NotNull
    @Size(min = 5, max = 15)
    @Pattern(regexp = "[a-zA-Z]{5}[a-zA-Z]*",
            message = "The username must be at least 5 characters, & must be alphabetic")
    private String username;

    @NotNull
    @Size(min = 4, max = 15)
    private String password;

    @NotNull
    @Size(min = 5, max = 15)
    @Email
    //@Email(regexp = "[\\w+?]@[\\w+?]+\\.\\w+?")
    private String email;

    private Date date;
    private static int nextId = 0;

    public User() {
        this.userId = nextId;
        nextId++;
        this.date = new Date();
    }

    public User(String username, String password, String email) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date aDate) {
        this.date = aDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int aId) {
        this.userId = aId;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int aNextId) {
        User.nextId = aNextId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String aUser) {
        this.username = aUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String aPassword) {
        this.password = aPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String aEmail) {
        this.email = aEmail;
    }
}
