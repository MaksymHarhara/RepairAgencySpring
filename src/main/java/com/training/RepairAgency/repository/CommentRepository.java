package com.training.RepairAgency.repository;

import com.training.RepairAgency.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAll();
}
