package com.training.RepairAgency.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestInfoDTO {

    long id;
    String master;
    BigDecimal price;
    String  reason;
}
