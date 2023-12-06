package com.Electro.ServiceI;

import com.Electro.Dto.CategoryDto;
import com.Electro.Dto.PageableResponse;

public interface CategoryServiceI {

    CategoryDto Createcategory(CategoryDto categoryDto);

     PageableResponse getAllcategory(Integer pageNumber, Integer pageSize, String sortBy, String direction);

     CategoryDto getSingleCategory(String categoryId);

     void deleteCategory(String categoryId);

     CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);

}
