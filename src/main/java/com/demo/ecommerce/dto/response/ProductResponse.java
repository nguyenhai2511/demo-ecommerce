package com.demo.ecommerce.dto.response;

import com.demo.ecommerce.entity.Product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
	String id;
	String code;
	String category;
	String brand;
	String type;
	String description;

	public ProductResponse (Product product) {
		this.id = product.getId();
		this.code = product.getCode();
		this.category = product.getCategory();
		this.brand = product.getBrand();
		this.type = product.getType();
		this.description = product.getDescription();
	}

	public static ProductResponse convertFromProduct(Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId(product.getId());
		productResponse.setCode(product.getCode());
		productResponse.setBrand(product.getBrand());
		productResponse.setCategory(product.getCategory());
		productResponse.setType(product.getType());
		productResponse.setDescription(product.getDescription());
		return productResponse;
	}

}
