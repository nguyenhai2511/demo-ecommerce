package com.demo.ecommerce.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {
	@NotEmpty(message = "Code is required. This field must not be empty")
	@Size(min = 3, max = 6, message = "Code must be at least 3 characters long")
	String code;
	@NotEmpty(message = "Category is required. This field must not be empty")
	String category;
	@NotEmpty(message = "Brand is required. This field must not be empty")
	String brand;
	@NotEmpty(message = "Type is required. This field must not be empty")
	String type;
	@NotEmpty(message = "Description is required. This field must not be empty")
	String description; 
}
