package com.project.Shopapp.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is not blank")
    private String phoneNumber;

    @JsonProperty("password")
    @NotBlank(message = "password is not blank")
    private String password;



}
