package com.Electro.ServiceImpl;

import com.Electro.Constanst.AppConstant;
import com.Electro.Dto.PageableResponse;
import com.Electro.Dto.ProductDto;
import com.Electro.Entity.Category;
import com.Electro.Entity.Product;
import com.Electro.Exception.ResourceNotFoundException;
import com.Electro.Helper.Helper;
import com.Electro.Repository.CategoryRepository;
import com.Electro.Repository.ProductRepository;
import com.Electro.ServiceI.ProductServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@Slf4j
public class ProductServiceImpl implements ProductServiceI {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        log.info("Entering the Dao call for create the Product : {}",productDto);
        Product product = this.modelMapper.map(productDto, Product.class);
        Date date = new Date();
        String id = UUID.randomUUID().toString();
        product.set(id);
        product.setAddedDate(date);
        Product newproduct = this.productRepository.save(product);
        log.info("Complete the Dao call for create the Product : {}",productDto);
        return modelMapper.map(newproduct, ProductDto.class);

    }

    @Override
    public ProductDto getSingleProduct(String productId) {
        log.info("Entering the Dao call for Get the Product With Product Id : {}",productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND + productId));
        log.info("Complete the Dao call for Get the Product With Product Id : {}",productId);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void deleteProduct(String productId) {
        log.info("Entering the Dao call for Delete the Product With Product Id : {}",productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND));
        log.info("Completed the Dao call for Delete the Product With Product Id : {}",productId);
        productRepository.delete(product);

    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto productDto) {
        log.info("Entering the Dao call for Update the Product With Product Id : {}",productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND));

        product.setDescription(productDto.getDescription());
        product.setTitle(productDto.getTitle());
        product.setLive(productDto.getLive());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setStock(productDto.getStock());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setImage(productDto.getImage());
        Product save = this.productRepository.save(product);
        log.info("Complete the Dao call for Update the Product With Product Id : {}",productId);
        return modelMapper.map(save, ProductDto.class);
    }


    @Override
    public PageableResponse<ProductDto> getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        log.info("Entering the Dao call for Get All Products : ");
        Sort sort = (direction.equalsIgnoreCase("desc")) ?
                (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        PageRequest pages = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> all = productRepository.findAll(pages);
        log.info("Completed the Dao call for Get All Products : ");
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(all, ProductDto.class);

        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> findByliveTrue(Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        log.info("Entering the Dao call for Get All Products Live True : ");
        Sort sort = (direction.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        PageRequest pages = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> byLiveTrue = productRepository.findByLiveTrue(pages);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(byLiveTrue, ProductDto.class);
        log.info("Completed the Dao call for Get All Products Live True : ");
        return pageableResponse;
    }


    @Override
    public PageableResponse<ProductDto> getAllLIveProducts(Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        log.info("Entering the Dao call for Get All Products : ");
        Sort sort = (direction.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        PageRequest pages = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> allProducts = productRepository.findAll(pages);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(allProducts, ProductDto.class);
        log.info("Completed the Dao call for Get All Products : ");
        return pageableResponse;
    }


    @Override
    public PageableResponse<ProductDto> getProductByTitle(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        log.info("Entering the Dao call for Get All Products With Keyword  :{} ", keyword);
        Sort sort = (direction.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        PageRequest pages = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> byTitleContaining = productRepository.findByTitleContaining(pages, keyword);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(byTitleContaining, ProductDto.class);
        log.info("Completed the Dao call for Get All Products With Keyword  :{} ", keyword);
        return pageableResponse;
    }

    @Override
    public ProductDto createProductWithCategory(ProductDto productDto, String categoryId) {
        log.info("Entering the Dao call for Create Products With CategoryId :{} ", categoryId);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND));

        Product product = this.modelMapper.map(productDto, Product.class);

        Date date = new Date();
        String id = UUID.randomUUID().toString();
        product.setProductId(id);
        product.setAddedDate(date);
        product.setCategories(category);
        Product newProduct = this.productRepository.save(product);
        log.info("Completed the Dao call for Create Products With CategoryId :{} ", categoryId);
        return modelMapper.map(newProduct, ProductDto.class);

    }

    @Override
    public PageableResponse<ProductDto> getAllOfCategory(String categoryId, Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        log.info("Entering the Dao call for Get All Products With Category Id :{} ", categoryId);
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND));
        Sort sort = (direction.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        PageRequest pages = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> product = this.productRepository.findByCategories(category, pages);

        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(product, ProductDto.class);
        log.info("Completed the Dao call for Get All Products With Category Id :{} ", categoryId);
        return pageableResponse;
    }


    @Override
    public ProductDto updateCategory(String productId, String categoryId) {
        log.info("Entering the Dao call for Update The Product With Category Id :{} ", categoryId);
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND));

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.NOT_FOUND));


        product.setCategories(category);
        Product save = this.productRepository.save(product);

        ProductDto dto = modelMapper.map(save, ProductDto.class);
        log.info("Completed the Dao call for Update The Product With Category Id :{} ", categoryId);
        return dto;
    }
}
