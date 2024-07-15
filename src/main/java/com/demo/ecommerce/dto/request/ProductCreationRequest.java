package com.demo.ecommerce.dto.request;

import jakarta.validation.constraints.NotEmpty;
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
	String code;
	@NotEmpty(message = "Category is required. This field must not be empty")
	String category;
	@NotEmpty(message = "Brand is required. This field must not be empty")
	String brand;
	@NotEmpty(message = "Type is required. This field must not be empty")
	String type;
	@NotEmpty(message = "Description is required. This field must not be empty")
	String description;

	public ProductCreationRequest convertFromStringToNull() {
		if (this.code.equalsIgnoreCase("null"))
			this.code = null;
		if (this.category.equalsIgnoreCase("null"))
			this.category = null;
		if (this.brand.equalsIgnoreCase("null"))
			this.brand = null;
		if (this.type.equalsIgnoreCase("null"))
			this.type = null;
		if (this.description.equalsIgnoreCase("null"))
			this.description = null;
		return this;
	}
}
