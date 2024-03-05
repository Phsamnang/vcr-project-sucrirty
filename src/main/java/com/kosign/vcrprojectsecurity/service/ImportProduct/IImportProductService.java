package com.kosign.vcrprojectsecurity.service.ImportProduct;


import com.kosign.vcrprojectsecurity.payload.importDetial.ImportDetailMainResponse;
import com.kosign.vcrprojectsecurity.payload.importDetial.ImportDetailRequest;

import java.time.LocalDate;

public interface IImportProductService {
    void createImportProduct();
    void createImportDetail(ImportDetailRequest payload);
    ImportDetailMainResponse getImportDetailById(LocalDate date);
}
