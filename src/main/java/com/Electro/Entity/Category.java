package com.Electro.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Category {

    @Id
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_image")
    private String title;

    @Column(name = "category_description")
    private String description;

    @Column(name = "catergory_image")
    private String coverImage;





}
