package com.training.RepairAgency.service;


import com.training.RepairAgency.dto.RequestDTO;
import com.training.RepairAgency.dto.UserDTO;
import com.training.RepairAgency.dto.UsersDTO;
import com.training.RepairAgency.entity.Role;
import com.training.RepairAgency.entity.User;
import com.training.RepairAgency.repository.UserRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UsersDTO getAllUsers() {
        return new UsersDTO(userRepository.findAll());
    }

    public Optional<User> findById(@NonNull Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(@NonNull String email) {
        return userRepository.findByEmail(email);
    }

    public boolean saveUser(UserDTO userDTO) {
        try {
            userRepository.save(User.builder().email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .isBanned(false)
                    .enabled(true)
                    .firstName(userDTO.getName())
                    .surname(userDTO.getSurname())
                    .money(BigDecimal.valueOf(0.0))
                    .roles(Arrays.asList(new Role(userDTO.getRole()))).build());

        } catch (Exception ex) {
            log.info("{Почтова адреса вже існує}");
            return false;
        }
        return true;
    }

    public boolean saveUser1(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException("user " + userDTO.getEmail() + " was not found!"));
        try {
            userRepository.updateMoneyById(userDTO.getMoney(), user.getId());

        } catch (Exception ex) {
            log.info("{Почтова адреса вже існує}");
            return false;
        }
        return true;
    }

    public boolean saveUser(User user) {
        try {
            if (userRepository.findByEmail(user.getEmail()).get()!=null) {
                return false;
            }
            userRepository.save(user);
        } catch (Exception ex) {
            log.info("{Почтова адреса вже існує}");
            return false;
        }
        return true;
    }

   // public boolean updateMoney(Long id, BigDecimal price) {
   //     User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() ->
   //             new UsernameNotFoundException("user " + userDTO.getEmail() + " was not found!"));
//
    //    userRepository.updateMoneyByEmail(userDTO.getMoney().subtract(price), user.getEmail());
   //     return true;
   // }

    public boolean updatePassword(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException("user " + userDTO.getEmail() + " was not found!"));

        userRepository.updatePasswordById(passwordEncoder.encode(userDTO.getPassword()), user.getId());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user = findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));


        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                !user.getIsBanned(),
                mapRolesToAuthorities(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public List<String> findByRole(String role) {
        return userRepository.findEmailByRole(role)
                .orElseThrow(RuntimeException::new);
    }
}

