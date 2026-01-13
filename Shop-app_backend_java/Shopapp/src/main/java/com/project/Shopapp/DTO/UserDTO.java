package com.project.Shopapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is not blank")
    private String phoneNumber;


    private String address;

    @NotBlank(message = "Password is not blank")
    private String password;


    private String retypePassword;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("facebook_account_id")
    private int FaceBookAcountId;

    @JsonProperty("google_account_id")
    private int GoogleAccountId;

    @JsonProperty("role_id")
    private int roleID;



}
