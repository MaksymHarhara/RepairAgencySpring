package com.training.RepairAgency.repository;

import com.training.RepairAgency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    String UPDATE_PASSWORD_BY_ID = "UPDATE User u SET u.password = ?1 WHERE u.id = ?2";

    String FIND_EMAIL_BY_ROLE = "SELECT user.email FROM user RIGHT JOIN user_roles ON user.id=user_roles.user_id INNER JOIN role ON user_roles.roles_id=role.id WHERE role.name=:role";

    String FIND_BY_ROLE = "SELECT user.* FROM user RIGHT JOIN  user_roles ON user.id=user_roles.user_id INNER JOIN role ON user_roles.roles_id=role.id WHERE role.name=:role";


    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(UPDATE_PASSWORD_BY_ID)
    void updatePasswordById(String password, Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.money = ?1 WHERE u.id = ?2", nativeQuery = true)
    void updateMoneyById(BigDecimal money, Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.money = ?1 WHERE u.email = ?2", nativeQuery = true)
    void updateMoneyByEmail(BigDecimal money, String email);

    @Query(value = FIND_BY_ROLE,
    nativeQuery = true)
    Optional<List<User>>findByRole(@Param("role")String role);

    Optional<Long>findIdByEmail(String email);

    @Query(value = FIND_EMAIL_BY_ROLE,
            nativeQuery = true)
    Optional<List<String>> findEmailByRole(@Param("role")String role);


}


