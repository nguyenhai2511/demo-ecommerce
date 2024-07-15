package com.demo.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.ecommerce.dto.request.ProductCreationRequest;
import com.demo.ecommerce.dto.request.ProductUpdateRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.service.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Validated
public class ProductController {
	ProductService productService;

	@GetMapping
	ModelAndView getProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		log.info("Get all products with pagination - page: {}, size: {}", page, size);
		Page<ProductResponse> productPage = productService.getProducts(page, size);
		ModelAndView modelAndView = new ModelAndView("products");
		modelAndView.addObject("productPage", productPage);
		modelAndView.addObject("currentPage", page);
		modelAndView.addObject("totalPages", productPage.getTotalPages());
		return modelAndView;
	}

	@PostMapping("/edit/{productId}")
	String updateProduct(@PathVariable String productId, @Valid @ModelAttribute("product") ProductUpdateRequest productUpdateRequest,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		log.info("Edit product with id = ", productId);
		if (bindingResult.hasErrors()) {
			productUpdateRequest.setId(productId);
			return "edit-product";
		}
		productService.updateProduct(productId, productUpdateRequest.convertFromStringToNull());
		return "redirect:/products";
	}

	@GetMapping("/edit/{productId}")
	public String showEditForm(@PathVariable("productId") String productId, Model model) {
		log.info("Get product by id and route to edit page");
		ProductResponse product = productService.getProduct(productId);
		model.addAttribute("product", product);
		return "edit-product";
	}

	@GetMapping("/new")
	public String showCreateProductForm(Model model) {
		model.addAttribute("product", new ProductCreationRequest());
		return "create-product";
	}

	@PostMapping("/new")
	public String createProduct(@Valid @ModelAttribute("product") ProductCreationRequest product,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("product", product);
			return "create-product";
		}
		productService.createProduct(product.convertFromStringToNull());
		return "redirect:/products";
	}
}
