package com.demo.ecommerce.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecommerce.dto.request.ProductCreationRequest;
import com.demo.ecommerce.dto.request.ProductUpdateRequest;
import com.demo.ecommerce.dto.response.ApiResponse;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.enums.HttpStatusCode;
import com.demo.ecommerce.service.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApiProductController {
	ProductService productService;

	@PostMapping
	ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request) {
		return ApiResponse.
				<ProductResponse>builder()
				.code(HttpStatusCode.CREATED.getCode())
				.result(productService.createProduct(request))
				.build();
	}

	@GetMapping
	ApiResponse<List<ProductResponse>> getProducts(Model model) {
		log.info("Get all products");
		return ApiResponse
				.<List<ProductResponse>>builder()
				.code(HttpStatusCode.OK.getCode())
				.result(productService.getProducts())
				.build();
	}

	@GetMapping("/{productId}")
	ApiResponse<ProductResponse> getProduct(@PathVariable("productId") String productId) {
		return ApiResponse
				.<ProductResponse>builder()
				.code(HttpStatusCode.OK.getCode())
				.result(productService.getProduct(productId))
				.build();
	}


	@DeleteMapping("/{productId}")
	ApiResponse<String> deleteProduct(@PathVariable String productId) {
		productService.deleteProduct(productId);
		return ApiResponse
				.<String>builder()
				.code(HttpStatusCode.NO_CONTENT.getCode())
				.result("Product has been deleted")
				.build();
	}

	@PutMapping("/{productId}")
	ApiResponse<ProductResponse> updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
		return ApiResponse
				.<ProductResponse>builder()
				.code(HttpStatusCode.OK.getCode())
				.result(productService.updateProduct(productId, request))
				.build();
	}
}
