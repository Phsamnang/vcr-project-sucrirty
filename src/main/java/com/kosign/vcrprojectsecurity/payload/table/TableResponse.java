package com.kosign.vcrprojectsecurity.payload.table;

import lombok.Builder;

@Builder
public record TableResponse(String name,String status) {
}
