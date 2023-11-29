package com.Electro.Dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @NotBlank
    @Size(min = 5,max = 20,message = "UserName Should be Min 5 Character And Max 20 Character")
    private String userName;

    @NotBlank
    @Size(min = 3,max = 6,message = "Enter Valid Gender For User")
    private String userGender;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Enter The Valid Email Id")
    private String userEmail;

    @NotBlank(message = "Password Must be Required")
    private String userPassword;

    @NotBlank
    @Size(message = "Please Enter About User")
    private String about;

    @ImageNameValid(message = "Image Name Must Not  Be Blank")
    private String imageName;


}