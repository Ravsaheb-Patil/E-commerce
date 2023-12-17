package com.Electro.Repository;

import com.Electro.Entity.Category;
import com.Electro.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    Page<Product> findByLiveTrue(Pageable pageable);

    Page<Product> findByTitleContaining(Pageable pageable,String keyword);


    Page<Product> findByCategories(Category category,Pageable pageable);
}
