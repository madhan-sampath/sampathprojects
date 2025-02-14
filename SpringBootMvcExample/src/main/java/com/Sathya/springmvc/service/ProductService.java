package com.Sathya.springmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sathya.springmvc.entity.ProductEntity;
import com.Sathya.springmvc.model.ProductModel;
import com.Sathya.springmvc.repository.productRepository;

@Service
public class ProductService {
	@Autowired
	productRepository productRepository;
	
	public void saveProductDetails(ProductModel productModel) {

		double taxRate;
    	taxRate = 18;
    	double discountPrice;
    	discountPrice =productModel.getPrice() * productModel.getDiscountRate()/100;
    	
    	double offerPrice;
    	offerPrice = productModel.getPrice() - discountPrice;
    	
    	double finalPrice;
    	finalPrice = offerPrice + ((taxRate/100)*offerPrice);
    	
    	double stackValue;
    	stackValue = productModel.getPrice() * productModel.getQuantity();
    	
    	ProductEntity productEntity=new ProductEntity();
    	productEntity.setName(productModel.getName());
    	productEntity.setBrand(productModel.getBrand());
    	productEntity.setMadeIn(productModel.getMadeIn());
    	productEntity.setPrice(productModel.getPrice());
    	productEntity.setQuantity(productModel.getQuantity());
    	productEntity.setDiscountRate(productModel.getDiscountRate());
    	
    	productEntity.setDiscountPrice(discountPrice);
    	productEntity.setOfferPrice(offerPrice);
    	productEntity.setTaxRate(taxRate);
    	productEntity.setFinalPrice(finalPrice);
    	productEntity.setStackValue(stackValue);


    	productRepository.save(productEntity);
	}

	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> products=productRepository.findAll();
		return products;
		
	}
	public ProductEntity searchById(Long id) {
		Optional<ProductEntity> optionalData= productRepository.findById(id);
		if(optionalData.isPresent()) {
			ProductEntity product = optionalData.get();
			return product;
		}
		else {
			return null;
		}
		
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	public ProductModel showEditForm(Long id) {
		Optional<ProductEntity> optionalData= productRepository.findById(id);
		ProductEntity productEntity= optionalData.get();
		ProductModel productModel=new ProductModel();
		productModel.setName(productEntity.getName());
		productModel.setBrand(productEntity.getBrand());
		productModel.setMadeIn(productEntity.getMadeIn());
		productModel.setPrice(productEntity.getPrice());
		productModel.setQuantity(productEntity.getQuantity());
		productModel.setDiscountRate(productEntity.getDiscountRate());
		return productModel;
		
	}

	public void editById(Long id, ProductModel productModel) {
		Optional<ProductEntity> optionalData= productRepository.findById(id);
		if(optionalData.isPresent())
		{
		ProductEntity productEntity= optionalData.get();
		double taxRate;
    	taxRate = 18;
    	double discountPrice;
    	discountPrice =productModel.getPrice() * productModel.getDiscountRate()/100;
    	
    	double offerPrice;
    	offerPrice = productModel.getPrice() - discountPrice;
    	
    	double finalPrice;
    	finalPrice = offerPrice + ((taxRate/100)*offerPrice);
    	
    	double stackValue;
    	stackValue = productModel.getPrice() * productModel.getQuantity();
    	
    	productEntity.setName(productModel.getName());
    	productEntity.setBrand(productModel.getBrand());
    	productEntity.setMadeIn(productModel.getMadeIn());
    	productEntity.setPrice(productModel.getPrice());
    	productEntity.setQuantity(productModel.getQuantity());
    	productEntity.setDiscountRate(productModel.getDiscountRate());
    	
    	productEntity.setDiscountPrice(discountPrice);
    	productEntity.setOfferPrice(offerPrice);
    	productEntity.setTaxRate(taxRate);
    	productEntity.setFinalPrice(finalPrice);
    	productEntity.setStackValue(stackValue);

    	productRepository.save(productEntity);
		}
		
	}
}
