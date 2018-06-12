package org.launchcode.cheesemvc.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
//import java.util.regex.Pattern;

public class UserForm {

    @NotNull
    @Size(min = 5, max = 15)
    @Pattern(regexp = "[a-zA-Z]{5}[a-zA-Z]*",
            message = "The username must be at least 5 characters, & must be alphabetic")
    private String username;

    @NotNull
    @Size(min = 4, max = 15)
    private String password;

    @NotNull(message = "Passwords do not match")
    private String verifyPassword;

    @NotNull
    @Size(min = 5, max = 15)
    @Email
    //@Email(regexp = "[\\w+?]@[\\w+?]+\\.\\w+?")
    private String email;


    public UserForm() {}

    public UserForm(String username, String password, String email) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
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

    public void setPassword(String aPassword)
    {
        this.password = aPassword;
        checkPassword();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String aEmail) {
        this.email = aEmail;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String aVerifyPassword) {
        this.verifyPassword = aVerifyPassword;
        checkPassword();
    }

    private void checkPassword()
    {
        if((!(password == null)) && !(verifyPassword == null))
        {
            if(!(password.equals(verifyPassword)))
            {
                verifyPassword = null;
            }
        }
    }
}
