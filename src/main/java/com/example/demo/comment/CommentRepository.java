package com.example.demo.comment;

import com.example.demo.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllById(Integer id);

    List<Comment> findAllByAnswer(Answer answer);
}
