package com.kosign.vcrprojectsecurity.service.product;


import com.kosign.vcrprojectsecurity.payload.product.ProductPriceRequest;
import com.kosign.vcrprojectsecurity.payload.product.ProductRequest;
import com.kosign.vcrprojectsecurity.payload.product.ProductResponse;

import java.util.List;

public interface IProductService {
   void createProduct(ProductRequest request);

   void updateImageProduct(Long Id,String imageUrl);
   List<ProductResponse>getAllProducts();

   void productPrice(ProductPriceRequest request);
}
