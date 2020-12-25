package go.springboot.learnrestmicroservice.ui.model.request;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInfoRequestModel {
    @NotNull(message = "firstName cannot be null")
    @Size(min = 1, max=30, message = "firstName size min is 1, max is 30")
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    @Size(min = 1, max=30, message = "lastName size min is 1, max is 30")
    private String lastName;
    @Email(message = "email is not valid")
    private String email;
    @Size(min = 1, max=8)
    private String userId;

    public UserInfoRequestModel(String firstName, String lastName, String email, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
    }

    public UserInfoRequestModel() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
