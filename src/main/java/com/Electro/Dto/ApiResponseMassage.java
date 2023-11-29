package com.Electro.Dto;


import lombok.*;
import org.springframework.http.HttpStatus;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMassage {

    public String message;
    public Boolean status;
    public HttpStatus statusCode;
}




