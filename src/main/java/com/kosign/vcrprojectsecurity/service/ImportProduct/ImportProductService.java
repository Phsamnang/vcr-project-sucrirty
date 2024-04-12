package com.kosign.vcrprojectsecurity.service.ImportProduct;


import com.kosign.vcrprojectsecurity.domiain.Import.ImportDetail;
import com.kosign.vcrprojectsecurity.domiain.Import.ImportDetailRepository;
import com.kosign.vcrprojectsecurity.domiain.Import.ImportProduct;
import com.kosign.vcrprojectsecurity.domiain.Import.ImportProductRepository;
import com.kosign.vcrprojectsecurity.domiain.product.Product;
import com.kosign.vcrprojectsecurity.domiain.product.ProductRepository;
import com.kosign.vcrprojectsecurity.domiain.stock.StockRepository;
import com.kosign.vcrprojectsecurity.domiain.user.UserRepository;
import com.kosign.vcrprojectsecurity.exception.EntityNotFoundException;
import com.kosign.vcrprojectsecurity.helper.AuthHelper;
import com.kosign.vcrprojectsecurity.payload.importDetial.ImportDetailMainResponse;
import com.kosign.vcrprojectsecurity.payload.importDetial.ImportDetailRequest;
import com.kosign.vcrprojectsecurity.payload.importDetial.ImportDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImportProductService implements IImportProductService {
    private final ImportProductRepository importProductRepository;
    private final ImportDetailRepository importDetailRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;

    @Override
    public void createImportProduct() {
        var email = AuthHelper.getUsername();
        var user = userRepository.findByEmail(email).get();
        importProductRepository.save(ImportProduct.builder().user(user).build());
    }

    @Override
    public void createImportDetail(ImportDetailRequest payload) {
        var product = productRepository.findById(payload.productId()).orElseThrow(() -> new EntityNotFoundException(Product.class, "Product not found!"));
        var importProduct = importProductRepository.findById(payload.importId()).orElseThrow(() -> new EntityNotFoundException(ImportProduct.class, "Import not found"));
        var importDeatail = importDetailRepository.findByProductAndImportProduct(product, importProduct);
        var amount = payload.importPrice().multiply(BigDecimal.valueOf(payload.importQty()));
        var addTotoTal = importProduct.getImportTotal().add(amount);
        var stock=stockRepository.findByProduct(product);

        if (importDeatail.isPresent()) {
            var newQty = importDeatail.get().getImportQty() + payload.importQty();
            importDeatail.get().setImportQty(newQty);
            importDeatail.get().setAmount(importDeatail.get().getImportPrice().multiply(BigDecimal.valueOf(importDeatail.get().getImportQty())));
            importProduct.setImportTotal(addTotoTal);
            importProduct.setImportUsdTotal(importProduct.getImportTotal().divide(BigDecimal.valueOf(4000)));
            importDetailRepository.save(importDeatail.get());
        } else {
            importDetailRepository.save(ImportDetail.builder()
                    .product(product)
                    .importProduct(importProduct)
                    .importPrice(payload.importPrice())
                    .importQty(payload.importQty())
                    .amount(amount).build()
            );

            //System.err.println("total import "+ addTotoTal);
            importProduct.setImportTotal(addTotoTal);
            importProduct.setImportUsdTotal(importProduct.getImportTotal().divide(BigDecimal.valueOf(4000)));

        }
        stock.setNumber(stock.getNumber()+ payload.importQty());
        stockRepository.save(stock);
        importProductRepository.save(importProduct);
    }

    @Override
    public ImportDetailMainResponse getImportDetailById(LocalDate date) {
        var imp=importProductRepository.findByImportDate(date);

        if (imp==null){
         return null;
        }
        List<ImportDetailResponse> importDetailResponses = imp.getImportDetails().stream().map(i -> ImportDetailResponse.builder().productName(i.getProduct().getName())
                .price(i.getImportPrice())
                .QTY(i.getImportQty())
                .category(i.getProduct().getCategory().getName())
                .amount(i.getAmount()).build()).collect(Collectors.toList());
        return ImportDetailMainResponse.builder()
                .importId(imp.getId())
                .usdTotal(imp.getImportUsdTotal())
                .importTotal(imp.getImportTotal())
                .importDetails(importDetailResponses)
                .build();
    }
}
