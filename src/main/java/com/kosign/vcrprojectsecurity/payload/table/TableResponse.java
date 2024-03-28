package com.kosign.vcrprojectsecurity.payload.table;

import lombok.Builder;

@Builder
public record TableResponse(Long id,String name,String status) {
}
