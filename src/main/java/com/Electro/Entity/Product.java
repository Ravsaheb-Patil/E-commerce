package com.Electro.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;
@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {


    @Id
    private String productId;

    @Column(name = "product_title")
    private String title;

    @Column(name = "product_description")
    private String description;

    @Column(name = "product_price")
    private Double price;

    @Column(name = "product_discountedPrice")
    private Double discountedPrice;

    @Column(name = "product_quantity")
    private Integer quantity;

    @Column(name = "product_addedDate")
    private Date addedDate;

    @Column(name = "product_live")
    private Boolean live;

    @Column(name = "product_stock")
    private Boolean stock;

    @Column(name = "product_image")
    private String image;

    @ManyToOne
    private Category categories;


    public void set(String id) {
    }
}



