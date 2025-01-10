package com.example.demo.comment;

import com.example.demo.answer.Answer;
import com.example.demo.answer.AnswerService;
import com.example.demo.question.Question;
import com.example.demo.question.QuestionService;
import com.example.demo.user.SiteUser;
import com.example.demo.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create1/{id}")
    public String createToQuestionComment(@PathVariable("id") Integer id, @Valid CommentForm commentForm, BindingResult result,
                                Principal principal, Model model){
        Question question = questionService.getQuestion(id);
        SiteUser siteUser = userService.getUser(principal.getName());
        if (result.hasErrors()){
            model.addAttribute(question);
            return "question_detail";
        }
        commentService.createToQuestion(commentForm.getContent(), question, siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create2/{id}")
    public String createToAnswerComment(@PathVariable("id") Integer id, @Valid CommentForm commentForm, BindingResult result,
                                Principal principal, Model model){
        Answer answer = answerService.getAnswer(id);
        SiteUser siteUser = userService.getUser(principal.getName());
        if (result.hasErrors()){
            model.addAttribute(answer);
            return "answer_detail";
        }
        commentService.createToAnswer(commentForm.getContent(), answer, siteUser);
        return String.format("redirect:/answer/detail/%s", id);
    }
}
