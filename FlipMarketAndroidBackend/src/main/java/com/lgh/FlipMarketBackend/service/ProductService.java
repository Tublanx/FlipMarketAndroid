package com.lgh.FlipMarketBackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lgh.FlipMarketBackend.dto.ProductDto;
import com.lgh.FlipMarketBackend.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDto> findAllByUserNum(Long userNum) {
		List<Object[]> results = productRepository.findAllByUserNum(userNum);

		return results.stream()
				.map(r -> new ProductDto(Long.parseLong(r[0].toString()), r[1].toString(),
						Integer.parseInt(r[2].toString()), r[3].toString(), Integer.parseInt(r[4].toString()),
						r[5].toString(), r[6].toString(), Integer.parseInt(r[7].toString())))
				.collect(Collectors.toList());
	}

}
