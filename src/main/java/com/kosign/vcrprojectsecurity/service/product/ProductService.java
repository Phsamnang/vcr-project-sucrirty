package com.kosign.vcrprojectsecurity.service.product;

import com.kosign.vcrprojectsecurity.common.api.StatusCode;
import com.kosign.vcrprojectsecurity.domiain.category.CategoryRepository;
import com.kosign.vcrprojectsecurity.domiain.product.Product;
import com.kosign.vcrprojectsecurity.domiain.product.ProductRepository;
import com.kosign.vcrprojectsecurity.domiain.stock.Stock;
import com.kosign.vcrprojectsecurity.domiain.stock.StockRepository;
import com.kosign.vcrprojectsecurity.enums.Currency;
import com.kosign.vcrprojectsecurity.exception.BusinessException;
import com.kosign.vcrprojectsecurity.exception.EntityNotFoundException;
import com.kosign.vcrprojectsecurity.payload.product.ProductPriceRequest;
import com.kosign.vcrprojectsecurity.payload.product.ProductRequest;
import com.kosign.vcrprojectsecurity.payload.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final StockRepository stockRepository;
    @Override
    public void createProduct(ProductRequest request) {
          BigDecimal riel=new BigDecimal(4000);
           var category= categoryRepository.findById(request.getCategoryId()).get();
      var product=repository.save(Product.builder().image(request.getImage())
                .isAvailable(request.getIsAvailable())
                .name(request.getName())
                .category(category).build());
      stockRepository.save(new Stock(product,0));
    }
    //vakhim

    @Override
    public void updateImageProduct(Long Id, String imageUrl) {
        var product=repository.findById(Id).orElseThrow(()->new EntityNotFoundException(Product.class,"id",Id.toString()));
        product.setImage(imageUrl);
        repository.save(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        var products=repository.findAll();
        List<ProductResponse> responses=products
                .stream().map(product -> ProductResponse.builder().productId(product.getId())
                        .productName(product.getName())
                        .stockQty(stockRepository.findByProduct(product).getNumber())
                        .rielPrice(product.getRielPrice())
                        .usdPrice(product.getPrice())
                        .categoryName(product.getCategory().getName()).build()).collect(Collectors.toList());
        return responses;
    }

    @Override
    public void productPrice(ProductPriceRequest request) {
        BigDecimal rielPrice;
        BigDecimal usdPrice;
        if (request.getCurrency()== Currency.RIEL){
            rielPrice=request.getAmount();
            usdPrice=rielPrice.divide(BigDecimal.valueOf(4000));
        }else {
            usdPrice=request.getAmount();
            rielPrice=usdPrice.multiply(BigDecimal.valueOf(4000));
        }
        var p =repository.findById(request.getProductId()).orElseThrow(()->new BusinessException(StatusCode.PRODUCT_NOT_FOUND));
        p.setRielPrice(rielPrice);
        p.setPrice(usdPrice);
        repository.save(p);
    }

    @Override
    public ProductResponse getProductByCategory(Long id) {
        var product=repository.getProductByCategoryId(id);
        System.err.println(" prodfsdfas "+product);
        return ProductResponse.builder()
                .productName(product.getName())
                .productId(product.getId()).build();
    }
}

