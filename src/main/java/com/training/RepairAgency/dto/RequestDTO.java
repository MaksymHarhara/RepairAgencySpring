package com.training.RepairAgency.dto;

import com.training.RepairAgency.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class RequestDTO {

    @NotNull(message = "{not.null}")
    Long id;

    @NotNull(message = "{not.null}")
    @Size(min = 5, max = 255, message = "{length.request}")
    String request;

    @NotNull(message = "{not.null}")
    String  status;

    //@NotNull(message = "{not.null}")
    BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    @NotNull(message = "{not.null}")
    String  reason;

    @NotNull(message = "{not.null}")
    LocalDateTime date;

  //  @NotNull(message = "{not.null}")
  //  String oplata;

}
