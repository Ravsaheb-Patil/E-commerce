package com.Electro.ServiceI;

import com.Electro.Dto.CategoryDto;
import com.Electro.Dto.PageableResponse;
import com.Electro.Dto.ProductDto;

public interface ProductServiceI {
    ProductDto saveProduct(ProductDto productDto);

    ProductDto getSingleProduct(String productId);

    void deleteProduct(String productId);

    ProductDto updateProduct(String productId,ProductDto productDto);

    PageableResponse<ProductDto> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String direction);

    //findByLive

    PageableResponse<ProductDto> findByliveTrue(Integer pageNumber, Integer pageSize, String sortBy, String direction);

    //getalllive

    PageableResponse<ProductDto> getAllLIveProducts(Integer pageNumber, Integer pageSize, String sortBy, String direction);

    //search By Title

    PageableResponse<ProductDto> getProductByTitle(String keyword,Integer pageNumber, Integer pageSize, String sortBy, String direction);

    // 3 Methods

    // create Product With Product And CategoryId
    ProductDto createProductWithCategory(ProductDto productDto,String categoryId);

    // getProduct With ProductId And CategoryId
    PageableResponse<ProductDto> getAllOfCategory(String categoryId,Integer pageNumber, Integer pageSize, String sortBy, String direction);
    // Update
    ProductDto updateCategory(String productId,String categoryId);



}




