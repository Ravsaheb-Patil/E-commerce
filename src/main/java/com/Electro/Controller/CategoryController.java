package com.Electro.Controller;

import com.Electro.Constanst.AppConstant;
import com.Electro.Dto.ApiResponseMassage;
import com.Electro.Dto.CategoryDto;
import com.Electro.Dto.ImageResponse;
import com.Electro.Dto.PageableResponse;
import com.Electro.ServiceI.CategoryServiceI;
import com.Electro.ServiceI.FileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {

    @Autowired
    CategoryServiceI categoryServiceI;

    @Autowired
    private FileService fileService;

    @Value("image/categories")
    private String path;
    /**
     * @author Ravsaheb Patil
     * @apiNote save Category Into Database
     * @param categoryDto
     * @return CategoryDto
     * @since 1.0v
     */

    @PostMapping("/")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        log.info("Enter the  request for Save the Category : {}", categoryDto);
        CategoryDto category = this.categoryServiceI.Createcategory(categoryDto);
        log.info("Complete the  request for Save the Category : {}", categoryDto);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);

    }
    /**
     * @author Ravsaheb Patil
     * @apiNote get All Category Into Database
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param direction
     * @return PageableResponse
     * @since 1.0v
     */
    @GetMapping("/")
    public ResponseEntity<CategoryDto> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "direction", defaultValue = AppConstant.SORT_DIR, required = false) String direction
    ) {
        log.info("Enter the  request for get All Category : ");
        PageableResponse<CategoryDto> allcategory = this.categoryServiceI.getAllcategory(pageNumber, pageSize, sortBy, direction);
        log.info("Complete the  request for get All Category : ");
        return new ResponseEntity(allcategory, HttpStatus.OK);
    }

    /**
     * @author Ravsaheb Patil
     * @apiNote get Single User By UserId
     * @param categoryId
     * @return CategoryDto
     * @since 1.0v
     */
    @GetMapping("/categoryId/{categoryId}")

    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable String categoryId) {
        log.info("Enter the  request for Get  the Category With Category Id  :{}",categoryId);
        CategoryDto singleCategory = this.categoryServiceI.getSingleCategory(categoryId);
        log.info("Complete the  request for Get  the Category With Category Id  :{}",categoryId);
        return new ResponseEntity<CategoryDto>(singleCategory, HttpStatus.OK);

    }
    /**
     * @author Ravsaheb Patil
     * @apiNote delete Category By Id
     * @param categoryId
     * @return ApiResponseMassage
     * @since 1.0v
     */
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponseMassage> deleteCategory(@PathVariable String categoryId) {
        log.info("Enter the  request for Delete  the Category With Category Id  :{}",categoryId);
        ApiResponseMassage api = new ApiResponseMassage();
        api.setMessage(AppConstant.DELETE);
        api.setStatusCode(HttpStatus.OK);
        api.setStatus(true);
        this.categoryServiceI.deleteCategory(categoryId);
        log.info("Completed the  request for Delete  the Category With Category Id  :{}",categoryId);
        return new ResponseEntity(api, HttpStatus.OK);
    }
    /**
     * @athor Ravsaheb Patil
     * @apiNote uodate Category By Id
     * @param dto
     * @param categoryId
     * @return CategoryDto
     * @since 1.0v
     */

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto dto, @PathVariable String categoryId) {
        log.info("Enter the  request for Update  the Category With Category Id  :{}",categoryId);
        CategoryDto categoryDto1 = this.categoryServiceI.updateCategory(dto, categoryId);
        log.info("Complete the  request for Update  the Category With Category Id  :{}",categoryId);
        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }
    /**
     * @author Ravsaheb Patil
     * @apiNote Upload the image with CategoryId
     * @param image
     * @param catId
     * @return
     * @throws IOException
     * @since 1.0v
     */
    @PostMapping("/image/{catId}")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam MultipartFile image, @PathVariable    String catId) throws IOException {
        log.info("Enter the request for Upload Image with categoryId : {}",catId);
        String file = this.fileService.uploadFile(image, path);
        CategoryDto singleCategory = this.categoryServiceI.getSingleCategory(catId);
        singleCategory.setCoverImage(file);
        CategoryDto categoryDto = this.categoryServiceI.updateCategory(singleCategory, catId);
        ImageResponse imageResponse = ImageResponse.builder().message("Image Uploaded").imageName(file).status(true).statusCode(HttpStatus.CREATED).build();
        log.info("Complete the request for Upload Image with categoryId : {}",catId);
        return new ResponseEntity<ImageResponse>(imageResponse,HttpStatus.CREATED);

    }
    /**
     * @author Ravsaheb Patil
     * @apiNote get image with CategoryId
     * @param categoryId
     * @param response
     * @throws IOException
     * @since 1.0v
     */
    @GetMapping("/image/{categoryId}")
    public void getUserImage(@PathVariable String categoryId, HttpServletResponse response) throws IOException {
        log.info("Enter the request for Get Image with categoryId : {}",categoryId);
        CategoryDto singleCategory = categoryServiceI.getSingleCategory(categoryId);
        log.info(" UserImage Name : {}",singleCategory.getCoverImage());
        InputStream resource = fileService.getResource(path, singleCategory.getCoverImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        log.info("Comlplete the request for Get Image with categoryId : {}",categoryId);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}

