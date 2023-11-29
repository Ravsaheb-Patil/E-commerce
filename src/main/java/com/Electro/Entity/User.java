package com.Electro.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class User {
    @Id

    private String userId;


    private String userName;


    private String userGender;

    private String userEmail;

    private String userPassword;

    private String about;

    private String imageName;




}
