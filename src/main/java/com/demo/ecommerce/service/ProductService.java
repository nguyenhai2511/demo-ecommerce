package com.demo.ecommerce.service;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.ecommerce.dto.request.ProductCreationRequest;
import com.demo.ecommerce.dto.request.ProductUpdateRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.exception.AppException;
import com.demo.ecommerce.exception.ErrorCode;
import com.demo.ecommerce.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {
	ProductRepository productRepository;
	Environment environment;

	public ProductResponse createProduct(ProductCreationRequest request) {
		Product product = new Product();
		product.setCode(request.getCode());
		product.setBrand(request.getBrand());
		product.setCategory(request.getCategory());
		product.setType(request.getType());
		product.setDescription(request.getDescription());
		try {
			product = productRepository.save(product);
		} catch (DataIntegrityViolationException exception) {
			throw new AppException(ErrorCode.PRODUCT_EXISTED);
		}
		return new ProductResponse(product);
	}

	public ProductResponse updateProduct(String productId, ProductUpdateRequest request) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new AppException(ErrorCode.PRODUCT_EXISTED));

		product.setCode(request.getCode());
		product.setBrand(request.getBrand());
		product.setCategory(request.getCategory());
		product.setType(request.getType());
		product.setDescription(request.getDescription());
		productRepository.save(product);

		return new ProductResponse(product);
	}

	public List<ProductResponse> getProducts() {
		log.info("Get Products");
		return productRepository.findAll().stream().map(ProductResponse::convertFromProduct).toList();
	}

	public ProductResponse getProduct(String productId) {
		return ProductResponse.convertFromProduct(
				productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_EXISTED)));
	}

	public void deleteProduct(String productId) {
		productRepository.deleteById(productId);
	}

	public Page<ProductResponse> getProducts(int page, int size) {
		Integer defaultRecordsSize = Integer.valueOf(environment.getProperty("page.size.default"));
		size = size > defaultRecordsSize ? defaultRecordsSize : size;
		Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));
		return productPage.map(ProductResponse::convertFromProduct);
	}
}
