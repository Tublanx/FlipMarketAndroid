package com.lgh.FlipMarketBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.lgh.FlipMarketBackend.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@NativeQuery("SELECT num, product_name, price, category, stock, description, image_path, like_count FROM product WHERE user_num <> ?1")
	List<Object[]> findAllByUserNum(Long userNum);
	
}
