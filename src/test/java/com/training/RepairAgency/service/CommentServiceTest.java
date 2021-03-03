package com.training.RepairAgency.service;

import com.training.RepairAgency.dto.CommentDTO;
import com.training.RepairAgency.dto.UserDTO;
import com.training.RepairAgency.entity.Comment;
import com.training.RepairAgency.entity.User;
import com.training.RepairAgency.repository.CommentRepository;
import com.training.RepairAgency.repository.UserRepository;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;

    UserRepository userRepository = mock(UserRepository.class);
    //private UserRepository userRepository;

    //@MockBean
    //private CommentDTO commentDTO;

    @Test
    public void saveComment() throws Exception {
        CommentDTO commentDTO = CommentDTO.builder().date(LocalDate.now()).comment("gghjfvjg")
                .username("moyayo85319@tigasu.com").build();

       // Mockito.when(userRepository.findByEmail(commentDTO.getComment()).orElse(null))
        //        .thenReturn(new User());
       // Mockito.doReturn("moyayo85319@tigasu.com")
      //          .when(userRepository)
      //          .findByEmail(commentDTO.getUsername())
      //          .orElseThrow(Exception::new);

        Comment comment1 = commentService.saveComment(commentDTO);

       // assertNotNull(comment1.getComment());
        assertNull(commentService.saveComment(commentDTO));
    }
}