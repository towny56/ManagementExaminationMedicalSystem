package com.fixit.areas.users.models.binding;

import com.fixit.areas.users.models.binding.validations.IsEmailRegistered;
import com.fixit.areas.users.models.binding.validations.IsPasswordMatching;
import com.fixit.areas.users.models.binding.validations.IsUsernameTaken;
import com.fixit.areas.users.models.binding.validations.IsWardPresent;
import com.fixit.constants.Constants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@IsPasswordMatching
public class UsersRegisterBindingModel {
    @Size(min = 4, max = 20, message = Constants.USERNAME_LENGTH)
    @IsUsernameTaken
    private String username;

    @Size(min = 4, max = 30, message = Constants.PASSWORD_LENGTH)
    private String password;

    private String confirmPassword;

    @NotEmpty(message = Constants.ENTER_VALID_EMAIL)
    @Email(message = Constants.ENTER_VALID_EMAIL)
    @IsEmailRegistered
    private String email;

    @Size(max = 50, message = Constants.FIRST_NAME_LENGTH)
    private String firstName;
    @Size(max = 50, message = Constants.LAST_NAME_LENGTH)
    private String lastName;
    @Size(max = 20, message = Constants.EGN_LENGTH)
    private String egn;

    private String roleName;
    @IsWardPresent
    private String wardName;

    public UsersRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
