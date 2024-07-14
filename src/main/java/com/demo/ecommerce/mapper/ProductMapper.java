package com.demo.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.demo.ecommerce.dto.request.ProductCreationRequest;
import com.demo.ecommerce.dto.request.ProductUpdateRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	Product toProduct(ProductCreationRequest request);

	ProductResponse toProductResponse(Product product);

	void updateUser(@MappingTarget Product product, ProductUpdateRequest request);
}
