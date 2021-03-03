package com.training.RepairAgency.service;

import com.training.RepairAgency.dto.CommentDTO;
import com.training.RepairAgency.dto.UserDTO;
import com.training.RepairAgency.entity.Role;
import com.training.RepairAgency.entity.User;
import com.training.RepairAgency.exception.store_exc.DuplicateUsernameException;
import com.training.RepairAgency.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final String PASSWORD = "password";
    private static final String ENCODED_PASSWORD = "encoded password";
    private static final String ROLE_USER = "ROLE_USER";

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDTO userDTO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService testInstance;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldSaveUser() {
        when(userDTO.getEmail()).thenReturn("harharaaamaksym@gmail.com");
        when(userDTO.getPassword()).thenReturn(PASSWORD);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
        when(userDTO.getName()).thenReturn("Maksymmm");
        when(userDTO.getSurname()).thenReturn("Harharaaa");
        when(userDTO.getMoney()).thenReturn(BigDecimal.valueOf(0.0));
        when(userDTO.getRole()).thenReturn(ROLE_USER);

         testInstance.saveUser(userDTO);

        verify(userDTO).setEmail("harharaaamaksym@gmail.com");
        verify(userDTO).setPassword(ENCODED_PASSWORD);
        verify(userDTO).setName("Maksymmm");
        verify(userDTO).setSurname("Harharaaa");
        verify(userDTO).setMoney(BigDecimal.valueOf(0.0));
        verify(userDTO).setRole(ROLE_USER);
        verify(userRepository).save(ArgumentMatchers.any(User.class));
    }

//  //  public void shouldThrowDuplicateUsernameException() {
  //      when(userDTO.getPassword()).thenReturn(PASSWORD);
  //      when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);
  //      when(userDTO.getRole()).thenReturn(ROLE_USER);
   //     doThrow(RuntimeException.class).when(userRepository).save(ArgumentMatchers.any(User.class));
   //     thrown.expect(DuplicateUsernameException.class);
  //      //thrown.expectMessage(ExceptionKeys.DUPLICATE_USERNAME);
//
   //     testInstance.saveUser(userDTO);
  //  }
}