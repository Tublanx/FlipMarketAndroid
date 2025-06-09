package com.lgh.FlipMarketBackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lgh.FlipMarketBackend.dto.ProductDto;
import com.lgh.FlipMarketBackend.service.ProductService;

@RestController
@RequestMapping("/api/android/")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/main")
	public ResponseEntity<?> getProductList(@RequestParam("userNum") Long userNum) {
		List<ProductDto> productList = productService.findAllByUserNum(userNum);
		return ResponseEntity.ok(productList);
	}

}
