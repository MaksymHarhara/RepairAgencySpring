package com.training.RepairAgency.service;

import com.training.RepairAgency.dto.RoleDTO;
import com.training.RepairAgency.dto.UserDTO;
import com.training.RepairAgency.entity.Role;
import com.training.RepairAgency.entity.User;
import com.training.RepairAgency.repository.RoleRepository;
import com.training.RepairAgency.repository.UserRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findById(@NonNull Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public boolean saveRole(RoleDTO roleDTO) {
        Role role = roleRepository.findById(roleDTO.getId()).orElseThrow(() ->
                new UsernameNotFoundException("user " + roleDTO.getId() + " was not found!"));
        System.out.println(roleDTO.getId());
        System.out.println(role.getId());
        System.out.println(roleDTO.getName());
        System.out.println(role.getName());
        try {
            roleRepository.updateRoleById(roleDTO.getName(), role.getId());
        } catch (Exception ex) {
            log.info("{Почтова адреса вже існує}");
            return false;
        }
        return true;
    }
}
