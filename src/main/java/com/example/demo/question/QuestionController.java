package com.example.demo.question;

import com.example.demo.answer.Answer;
import com.example.demo.answer.AnswerForm;
import com.example.demo.answer.AnswerService;
import com.example.demo.comment.CommentForm;
import com.example.demo.user.SiteUser;
import com.example.demo.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,@RequestParam(value = "kw", defaultValue = "")String kw) {
        Page<Question> paging = questionService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "question_list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm, CommentForm commentForm,
                         @RequestParam(value = "page", defaultValue = "0") int page, Answer answer){
        Question question = questionService.getQuestion(id);
        Page<Answer> paging2 = answerService.getList(page, question);
        model.addAttribute("question", question);
        model.addAttribute("paging2", paging2);
        model.addAttribute(answer);
        return "question_detail";
    }
    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }
    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal){
        SiteUser siteUser = userService.getUser(principal.getName());
        if (bindingResult.hasErrors()){
            return "question_form";
        }
        questionService.createQuestion(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }
    @GetMapping("/modify/{id}")
    @PreAuthorize("isAuthenticated()")
    public String questionModify(QuestionForm questionForm, @PathVariable("id")Integer id, Principal principal){
        Question question = questionService.getQuestion(id);
        if (!question.getSiteUser().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }
    @PostMapping("/modify/{id}")
    @PreAuthorize("isAuthenticated()")
    public String questionModify(@PathVariable("id") Integer id,
                                 @Valid QuestionForm questionForm, BindingResult result, Principal principal){
        if (result.hasErrors()){
            return "question_form";
        }
        Question question = questionService.getQuestion(id);
        if (!question.getSiteUser().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
    @GetMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String questionDelete(QuestionForm questionForm, @PathVariable("id")Integer id, Principal principal){
        Question question = questionService.getQuestion(id);
        if (!question.getSiteUser().getName().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        questionService.delete(question);
        return "redirect:/";
    }
    @GetMapping("/vote/{id}")
    @PreAuthorize("isAuthenticated()")
    public String questionVote(@PathVariable("id") Integer id, Principal principal){
        Question question = questionService.getQuestion(id);
        SiteUser siteUser = userService.getUser(principal.getName());
        questionService.vote(question, siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
}