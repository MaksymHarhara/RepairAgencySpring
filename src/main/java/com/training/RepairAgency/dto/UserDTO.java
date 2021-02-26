package com.training.RepairAgency.dto;

import com.training.RepairAgency.validation.UniqueEmail;
import com.training.RepairAgency.validation.ValidName;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @NotNull(message = "{not.null}")
    @Email(message = "{email.valid}")
    @Size(min = 5, max = 255, message = "{length.email}")
    @UniqueEmail
    private String email;

    @NotNull(message = "{not.null}")
    @ValidName
    @Size(min = 2, max = 255, message = "{length.name}")
    private String name;

    @NotNull(message = "{not.null}")
    @Size(min = 2, max = 255, message = "{length.surname}")
    @ValidName
    private String surname;

    @NotNull(message = "{not.null}")
    @Size(min = 5, max = 255, message = "{length.password}")
    private String password;

    @NotNull(message = "{not.null}")
    private String role;

    //@NotNull(message = "{not.null}")
    private BigDecimal money;
}
