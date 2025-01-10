package com.example.demo.comment;

import com.example.demo.answer.Answer;
import com.example.demo.question.Question;
import com.example.demo.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void createToQuestion(String content, Question question, SiteUser siteUser){
        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.now());
        comment.setContent(content);
        comment.setQuestion(question);
        comment.setSiteUser(siteUser);
        commentRepository.save(comment);
    }
    public void createToAnswer(String content, Answer answer, SiteUser siteUser){
        Comment comment = new Comment();
        comment.setCreateDate(LocalDateTime.now());
        comment.setContent(content);
        comment.setAnswer(answer);
        comment.setSiteUser(siteUser);
        commentRepository.save(comment);
    }
    public List<Comment> getCommentList(Answer answer){
        return commentRepository.findAllByAnswer(answer);
    }
}
