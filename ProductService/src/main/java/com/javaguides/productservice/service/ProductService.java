package com.javaguides.productservice.service;

import com.javaguides.productservice.payload.ProductRequest;
import com.javaguides.productservice.payload.ProductResponse;

public interface ProductService{

	long addProduct(ProductRequest productRequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);

	void deleteProductById(long productId);

}
