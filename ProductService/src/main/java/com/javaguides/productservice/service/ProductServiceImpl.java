package com.javaguides.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaguides.productservice.entity.Product;
import com.javaguides.productservice.exception.ProductCustomException;
import com.javaguides.productservice.payload.ProductRequest;
import com.javaguides.productservice.payload.ProductResponse;
import com.javaguides.productservice.repo.ProductRepo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepository;
	

	@Override
	public long addProduct(ProductRequest productRequest) {
		
		log.info("ProductServiceImpl | addProduct is called :::");
		
	    Product product=Product.builder().productName(productRequest.getName())
	    		.quantity(productRequest.getQuantity()).price(productRequest.getPrice()).build();
	    
	    product = productRepository.save(product);

        log.info("ProductServiceImpl | addProduct | Product Created");
        log.info("ProductServiceImpl | addProduct | Product Id : " + product.getProductId());
        return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long productId) {
		log.info("ProductServiceImpl ProductResponse | getProductById is called :::");
		
		Product product=productRepository.findById(productId)
		.orElseThrow(() -> new ProductCustomException("Product with given Id not found","Product_Not_Found"));
		
		log.info("ProductServiceImpl ProductResponse | getProductById findById is called :::");
		
//		ProductResponse pr=new ProductResponse();
//		pr.setPrice(product.getPrice());
		
		ProductResponse pr=ProductResponse.builder().price(product.getPrice())
		.productId(product.getProductId()).productName(product.getProductName())
		.quantity(product.getQuantity()).build();
		
		log.info("ProductServiceImpl ProductResponse | getProductById ::" + pr.getProductName());
		return pr;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) throws ProductCustomException{
		
		 log.info("Reduce Quantity {} for Id: {}", quantity,productId);

	        Product product
	                = productRepository.findById(productId)
	                .orElseThrow(() -> new ProductCustomException(
	                        "Product with given Id not found",
	                        "PRODUCT_NOT_FOUND"
	                ));

	        if(product.getQuantity() < quantity) {
	            throw new ProductCustomException(
	                    "Product does not have sufficient Quantity",
	                    "INSUFFICIENT_QUANTITY"
	            );
	        }

	        product.setQuantity(product.getQuantity() - quantity);
	        productRepository.save(product);
	        log.info("Product Quantity updated Successfully");
	}

	@Override
	public void deleteProductById(long productId) throws ProductCustomException{
		
		 log.info("Product id: {}", productId);

	        if (!productRepository.existsById(productId)) {
	            log.info("Im in this loop {}", !productRepository.existsById(productId));
	            throw new ProductCustomException(
	                    "Product with given with Id: " + productId + " not found:",
	                    "PRODUCT_NOT_FOUND");
	        }
	        log.info("Deleting Product with id: {}", productId);
	        productRepository.deleteById(productId);
	}

}
