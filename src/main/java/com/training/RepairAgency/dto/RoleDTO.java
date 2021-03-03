package com.training.RepairAgency.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    @NotNull(message = "{not.null}")
    Long id;
    @NotNull(message = "{not.null}")
    private String name;
}
