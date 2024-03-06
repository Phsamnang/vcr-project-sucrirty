package com.kosign.vcrprojectsecurity.service.sale;


import com.kosign.vcrprojectsecurity.domiain.menu.Menu;
import com.kosign.vcrprojectsecurity.domiain.menu.MenuRepository;
import com.kosign.vcrprojectsecurity.domiain.sale.Sale;
import com.kosign.vcrprojectsecurity.domiain.sale.SaleDetail;
import com.kosign.vcrprojectsecurity.domiain.sale.SaleDetailRepository;
import com.kosign.vcrprojectsecurity.domiain.sale.SaleRepository;
import com.kosign.vcrprojectsecurity.domiain.stock.StockRepository;
import com.kosign.vcrprojectsecurity.domiain.table.TableSale;
import com.kosign.vcrprojectsecurity.domiain.table.TableSaleRepository;
import com.kosign.vcrprojectsecurity.enums.SaleStatus;
import com.kosign.vcrprojectsecurity.enums.TableStatus;
import com.kosign.vcrprojectsecurity.exception.EntityNotFoundException;
import com.kosign.vcrprojectsecurity.payload.sale.SaleDetailRequest;
import com.kosign.vcrprojectsecurity.payload.sale.SaleDetailResponse;
import com.kosign.vcrprojectsecurity.payload.sale.SaleRequest;
import com.kosign.vcrprojectsecurity.payload.sale.SaleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {
    private final SaleRepository saleRepository;
    private final TableSaleRepository tableSaleRepository;
    private final MenuRepository menuRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final StockRepository stockRepository;

    @Override
    public void createSale(SaleRequest request) {
        var table = tableSaleRepository.findById(request.tableId()).orElseThrow(() -> new EntityNotFoundException(TableSale.class, "Table not found"));
        if (table.getStatus().equals(TableStatus.UNAVAILABLE.toString())) {
            throw new IllegalArgumentException("This table is using");
        }
        saleRepository.save(
                Sale.builder().tableSale(table).build()
        );
        table.setStatus(TableStatus.UNAVAILABLE.toString());
        tableSaleRepository.save(table);
    }

    @Override
    public void createOrder(SaleDetailRequest request) {
        var menu = menuRepository.findById(request.menuId()).orElseThrow(() -> new EntityNotFoundException(Menu.class, "Menu not found"));
        var sale = saleRepository.findById(request.saleId()).orElseThrow(() -> new EntityNotFoundException(Sale.class, "Sale not found"));
        saleDetailRepository.save(SaleDetail.
                builder().saleQty((int) request.qty())
                .salePrice(menu.getPrice()).saleAmount(menu.getPrice().multiply(BigDecimal.valueOf(request.qty())))
                .menu(menu).sale(sale).build());
        menu.getMenuDetails().stream().forEach(p -> {
            var stock = stockRepository.findByProduct(p.getProduct());
            stock.setNumber(stock.getNumber() - (p.getTotalUse() * request.qty()));
            stockRepository.save(stock);
        });

    }

    @Override
    public SaleResponse getSaleByTable(Long tableId) {
        var table=tableSaleRepository.findById(tableId).orElseThrow(()->new EntityNotFoundException(TableSale.class,"Table not found"));
        var sale=saleRepository.findByTableSaleAndStatus(table, SaleStatus.UNPAID.toString());

        List<SaleDetailResponse> saleDetailResponses=sale.getSaleDetails().stream().map(
                s->SaleDetailResponse.builder().item(s.getMenu().getName()).QTY(s.getSaleQty())
                        .price(s.getSalePrice()).amount(s.getSaleAmount()).status(s.getStatus()).build()
        ).collect(Collectors.toList());
        return SaleResponse.builder().tableName(table.getName()).totalAmount(sale.getSaleTotal()).orders(saleDetailResponses).build();
    }
}
