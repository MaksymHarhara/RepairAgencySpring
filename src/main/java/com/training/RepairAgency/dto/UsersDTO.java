package com.training.RepairAgency.dto;

import com.training.RepairAgency.entity.User;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsersDTO {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }
}
