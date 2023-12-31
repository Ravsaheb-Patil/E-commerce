package com.Electro.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;

    @NotBlank
    @Size(min = 2,max = 60,message = "Category Title Must Be Min 2 Character & Max 60 Character")
    private String title;

    @NotBlank
    @Size(min = 5,max = 500,message = "Category Description Must Be Min 5 Character & Max 400 Character")
    private String description;

    @ImageNameValid (message = "Image Name Must Not  Be Blank")
    private String coverImage;
}

