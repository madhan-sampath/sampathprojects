package com.Sathya.springmvc.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.Sathya.springmvc.entity.ProductEntity;
import com.Sathya.springmvc.model.ProductModel;
import com.Sathya.springmvc.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class ProductController {
		@Autowired
		ProductService productService;
		
		@GetMapping("")
		public String greet() {
			return "home";
		}
		
		@GetMapping("/home")
		public String greeting() {
			return "home";
		}
		/*   without default values
		 @GetMapping("/productform")
		public String getForm() {
					return "add-product";
			
		} */
		
		//with default values
		
		@GetMapping("/productform")
		public String getForm(Model model) {
			
			ProductModel productModel =new ProductModel();
			productModel.setMadeIn("India");
			productModel.setQuantity(1);
			productModel.setDiscountRate(10);
			model.addAttribute("productModel",productModel);
			
			return "add-product";
		}
		
		
		
		
		/* without default values
		@PostMapping("/saveproduct")
		public String saveProductDetails(ProductModel productModel) {
			productService.saveProductDetails(productModel);
			return "success";
		} */

		//validations with default values
		

		@PostMapping("/saveproduct")
		public String saveProductDetails(@Valid ProductModel productModel,BindingResult bindingResult,Model model) {
			HashMap<String, String> validationErrors=new HashMap<String,String>();
			if(bindingResult.hasErrors()) {
				for(FieldError fieldError:bindingResult.getFieldErrors())
				{
					validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
				}
				model.addAttribute("validationErrors", validationErrors);
				return "add-product";
			}	
			productService.saveProductDetails(productModel);
			
			return "redirect:/getallproducts";
			
		}
		
		
		//adding products to model class object
		//passing product details to view layer
		@GetMapping("/getallproducts")
		public String getAllProducts(Model model) {
			List<ProductEntity> products= productService.getAllProducts();
			model.addAttribute("products",products);
			return "product-list";
		}
		
		
		
		//searching product using id
		@GetMapping("/searchform")
		public String getSearchForm() {
			return "search-form";
		}
		@PostMapping("/searchbyid")
		public String searchById(@RequestParam Long id,Model model) {
			ProductEntity product = productService.searchById(id);
			model.addAttribute("product",product);
			return "search-form";
		}
		
		//delete product using id
		@GetMapping("/delete/{id}")
		public String deleteProductByID(@PathVariable("id") Long id) {
			productService.deleteById(id);
			return "redirect:/getallproducts";
		}

		//delete product using id
		@GetMapping("/edit/{id}")
		public String showEditForm(@PathVariable("id") Long id,Model model) {
			ProductModel product = productService.showEditForm(id);
			model.addAttribute("product",product);
			model.addAttribute("id",id);
			return "edit-product";
		}
		
		
		@PostMapping("/editbyid/{id}")
		public String editById(@PathVariable("id") Long id,ProductModel productModel) {
			productService.editById(id, productModel);
			return "redirect:/getallproducts";
		}
		
		
		
		
		
		
		
}
