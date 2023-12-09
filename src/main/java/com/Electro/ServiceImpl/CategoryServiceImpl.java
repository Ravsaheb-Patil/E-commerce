package com.Electro.ServiceImpl;

import com.Electro.Constanst.AppConstant;
import com.Electro.Dto.CategoryDto;
import com.Electro.Dto.PageableResponse;
import com.Electro.Entity.Category;
import com.Electro.Exception.ResourceNotFoundException;
import com.Electro.Helper.Helper;
import com.Electro.Repository.CategoryRepository;
import com.Electro.ServiceI.CategoryServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.UUID;

@Service
@Slf4j

public class CategoryServiceImpl implements CategoryServiceI {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto Createcategory(CategoryDto categoryDto) {
        log.info("Entering the Dao call for create the Category : {}",categoryDto);
        String id = UUID.randomUUID().toString();
        categoryDto.setCategoryId(id);
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = this.categoryRepository.save(category);
        log.info("Complete the Dao call for create the Category : {}",categoryDto);
        return modelMapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public PageableResponse getAllcategory(Integer pageNumber, Integer pageSize, String sortBy, String direction) {
     log.info("Entering the Dao call for get all categories :");
        Sort sort=(direction.equalsIgnoreCase("desc")?
                (Sort.by(sortBy)).ascending():(Sort.by(sortBy)).ascending());
        PageRequest pages = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> all = this.categoryRepository.findAll(pages);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(all, CategoryDto.class);
        log.info("Complete the Dao call for get all categories :");
        return pageableResponse;
    }

    @Override
    public CategoryDto getSingleCategory(String categoryId) {
        log.info("Entering the Dao call for Get Single Category With CategoryId :{}",categoryId);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND + categoryId));
        CategoryDto dto = modelMapper.map(category, CategoryDto.class);
        log.info("Complete the Dao call for Get Single Category With CategoryId :{}",categoryId);
        return dto;
    }

    @Override
    public void deleteCategory(String categoryId) {
        log.info("Entering the Dao call for Delete The Category With CategoryId :{}",categoryId);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND));
        this.categoryRepository.delete(category);
    }
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        log.info("Entering the Dao call for Update The Category With CategoryId :{}",categoryId);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND + categoryId));
        category.setDescription(categoryDto.getDescription());
        category.setDescription(categoryDto.getTitle());
        category.setCoverImage(categoryDto.getCoverImage());
        this.categoryRepository.save(category);
        CategoryDto dto = modelMapper.map(category, CategoryDto.class);
        log.info("Complete the Dao call for Update The Category With CategoryId :{}",categoryId);
        return dto;
    }
}
