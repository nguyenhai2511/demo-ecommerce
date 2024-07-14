package com.demo.ecommerce.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityScan
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String code;
	String category;
	String brand;
	String type;
	String description;
}
