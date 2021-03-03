package com.training.RepairAgency.repository;

import com.training.RepairAgency.entity.Role;
import com.training.RepairAgency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE Role r SET r.name = ?1 WHERE r.id = ?2", nativeQuery = true)
    void updateRoleById(String name, Long id);
}
