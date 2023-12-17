package com.Electro.Controller;

import com.Electro.Constanst.AppConstant;
import com.Electro.Dto.ApiResponseMassage;
import com.Electro.Dto.ImageResponse;
import com.Electro.Dto.PageableResponse;
import com.Electro.Dto.ProductDto;
import com.Electro.ServiceI.FileService;
import com.Electro.ServiceI.ProductServiceI;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/product")
@Slf4j
public class ProductCotroller {

    @Autowired
    private ProductServiceI productServiceI;

    @Value("images/products")
    private String path;

    @Autowired
    private FileService fileService;

    /**
     * @param productDto
     * @return ProductDto
     * @Autor Ravsaheb Patil
     * @apiNote save Product Into Database
     * @since 1.0v
     */
    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        log.info("Enter the  request for Save the Product : {}", productDto);
        ProductDto productDto1 = this.productServiceI.saveProduct(productDto);
        log.info("Completed the  request for Save the Product : {}", productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    /**
     * @param productId
     * @return productDto
     * @author Ravsaheb Patil
     * @apiNote get product With userId
     * @since 1.0v
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String productId) {
        log.info("Enter the  request for Get the Product With Product Id : {}", productId);
        ProductDto singleProduct = this.productServiceI.getSingleProduct(productId);
        log.info("Completed the  request for Get the Product With Product Id : {}", productId);
        return new ResponseEntity<>(singleProduct, HttpStatus.OK);
    }

    /**
     * @param productId
     * @return
     * @author Ravsaheb Patil
     * @apiNote delete product with productId
     * @since 1.0v
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMassage> deleteById(@PathVariable String productId) {
        log.info("Enter the  request for Delete  the Product with Product Id : {}", productId);
        ApiResponseMassage apiResponse = ApiResponseMassage.builder().message(AppConstant.DELETE).status(true).statusCode(HttpStatus.OK).build();
        this.productServiceI.deleteProduct(productId);
        log.info("Completed the  request for Delete  the Product with Product Id : {}", productId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    /**
     * @param productId
     * @param dto
     * @return
     * @author Ravsaheb Patil
     * @apiNote update product with productId
     * @since 1.0v
     */
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @PathVariable String productId, @RequestBody ProductDto dto) {
        log.info("Enter the  request for Update  the Product with Product Id : {}", productId);
        ProductDto productDto = this.productServiceI.updateProduct(productId, dto);
        log.info("Completed the  request for Update  the Product with Product Id : {}", productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param direction
     * @return
     * @author Ravsaheb Patil
     * @apiNote get All Products
     * @since 1.0v
     */
    @GetMapping("/")
    public ResponseEntity<PageableResponse> getAllProducts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.PRODUCT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "direction", defaultValue = AppConstant.SORT_DIR, required = false) String direction

    ) {
        log.info("Enter the  request for Get All Products : ");
        PageableResponse<ProductDto> allProducts = this.productServiceI.getAllProducts(pageNumber, pageSize, sortBy, direction);
        log.info("Completed the  request for Get All Products : ");
        return new ResponseEntity<>(allProducts, HttpStatus.OK);

    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param direction
     * @return
     * @author Ravsaheb Patil
     * @apiNote get All Live True Products
     * @since 1.0v
     */
    @GetMapping("/trueLiveProducts")
    public ResponseEntity<PageableResponse> findAllLiveTrueProducts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.PRODUCT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "direction", defaultValue = AppConstant.SORT_DIR, required = false) String direction
    ) {
        log.info("Enter the  request for Get All Live True  Products : ");
        PageableResponse<ProductDto> allLIveProducts = this.productServiceI.findByliveTrue(pageNumber, pageSize, sortBy, direction);
        log.info("Completed the  request for Get All Live True  Products : ");
        return new ResponseEntity<>(allLIveProducts, HttpStatus.OK);
    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param direction
     * @return
     * @author Ravsaheb Patil
     * @apiNote get All Live Products
     * @since 1.0v
     */

    @GetMapping("/liveProducts")
    public ResponseEntity<PageableResponse> getLiveProducts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.PRODUCT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "direction", defaultValue = AppConstant.SORT_DIR, required = false) String direction
    ) {
        log.info("Enter the  request for Get All Live   Products : ");
        PageableResponse<ProductDto> allLIveProducts = this.productServiceI.getAllLIveProducts(pageNumber, pageSize, sortBy, direction);
        log.info("Completed the  request for Get All Live   Products : ");
        return new ResponseEntity<>(allLIveProducts, HttpStatus.OK);

    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param direction
     * @param keyword
     * @return
     * @auhor Ravsaheb Patil
     * @apiNote get products Containing Keyword
     * @since 1.0v
     */
    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<PageableResponse> getProductByTitle(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.PRODUCT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "direction", defaultValue = AppConstant.SORT_DIR, required = false) String direction
            , @PathVariable String keyword) {
        log.info("Enter the  request for Get All  Products With Keyword :{} ", keyword);
        PageableResponse<ProductDto> productByTitle = this.productServiceI.getProductByTitle(keyword, pageNumber, pageSize, sortBy, direction);
        log.info("Completed the  request for Get All  Products With Keyword :{} ", keyword);
        return new ResponseEntity<>(productByTitle, HttpStatus.OK);
    }

    /**
     * @param image
     * @param productId
     * @return
     * @throws IOException
     * @author Ravsaheb Patil
     * @apiNote upload Image for Product with productId
     * @since 1.0v
     */
    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam MultipartFile image, @PathVariable String productId) throws IOException {
        log.info("Enter the  request for Upload Image With ProductId :{} ", productId);
        String file = this.fileService.uploadFile(image, path);

        ProductDto product = this.productServiceI.getSingleProduct(productId);

        product.setImage(file);

        ProductDto updatedProduct = this.productServiceI.updateProduct(productId, product);

        ImageResponse imageResponse = ImageResponse.builder().message("Image Uploaded").imageName(file).status(true).statusCode(HttpStatus.CREATED).build();
        log.info("Completed the  request for Upload Image With ProductId :{} ", productId);
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }

    /**
     * @param productId
     * @param response
     * @throws IOException
     * @author Ravsaheb Patil
     * @apiNote get Image with UserId
     * @since 1.0v
     */
    @GetMapping("/image/{categoryId}")
    public void getProductImage(@PathVariable String productId, HttpServletResponse response) throws IOException, FileNotFoundException {

        log.info("Enter the  request for Get Image With ProductId :{} ", productId);
        ProductDto product = this.productServiceI.getSingleProduct(productId);
        InputStream resource = this.fileService.getResource(path, product.getImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        log.info("Completed the  request for Get Image With ProductId :{} ", productId);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}




















