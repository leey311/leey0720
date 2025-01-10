package com.example.demo.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/sign")
    public String createUser(UserForm userForm){
        return "sing_up";
    }
    @PostMapping("/sign")
    public String createUser(@Valid UserForm userForm, BindingResult result){
        if(result.hasErrors()){
            return "sing_up";
        }
        if (!userForm.getPass1().equals(userForm.getPass2())){
            result.rejectValue("pass2", "passwordIncorrect", "비밀번호가 일치하지 않습니다.");
            return "sing_up";
        }
        try {
            userService.create(userForm.getName(), userForm.getPass1(), userForm.getEmail());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            result.reject("signup failed", e.getMessage());
            return "sing_up";
        }
        return "redirect:/";
    }
    @GetMapping("login")
    public String login(LoginForm loginForm){
        return "log_in";
    }
    @PostMapping("login")
    public String login(@Valid LoginForm loginForm , BindingResult result){
        if (result.hasErrors()) {
            return "log_in";
        }
        return "redirect:/";
    }
}
