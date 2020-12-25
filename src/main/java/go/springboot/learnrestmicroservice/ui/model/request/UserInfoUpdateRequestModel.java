package go.springboot.learnrestmicroservice.ui.model.request;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInfoUpdateRequestModel {
    @NotNull(message = "firstName cannot be null")
    @Size(min = 1, max=30, message = "firstName size min is 1, max is 30")
    private String firstName;
    @NotNull(message = "lastName cannot be null")
    @Size(min = 1, max=30, message = "lastName size min is 1, max is 30")
    private String lastName;


    public UserInfoUpdateRequestModel(String firstName, String lastName, String email, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserInfoUpdateRequestModel() {
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


    @Override
    public String toString() {
        return "UserInfoUpdateRequestModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
