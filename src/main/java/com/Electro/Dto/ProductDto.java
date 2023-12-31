package com.Electro.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {



    private String productId;

    @NotBlank
    @Size(min = 5,max = 50,message = "Product Title Should Be Minimum 5 Character & Maximum 50 Character ")
    private String title;

    @NotBlank
    @Size(min = 5,max = 500,message = "Product  Description Should Be Minimum 5 Character & Maximum 500 Character ")
    private String description;

    @NotNull(message = "Product Price Must Not Be Blank")
    private Double price;

    @NotNull(message = "Product Price Must Not Be Blank")
    private Double discountedPrice;

    @NotNull(message = "Quantity Should Not Be Blank")
    private Integer quantity;

    private Date addedDate;

    private Boolean live;

    private Boolean stock;

    private String image;

    private CategoryDto categories;
}