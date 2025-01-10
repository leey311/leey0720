package com.example.demo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    @NotEmpty(message = "이름은 필수 입력 사항입니다.")
    @Size(min = 3, max = 10)
    private String name;

    @NotEmpty(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(max = 10)
    private String pass1;
    @NotEmpty(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(max = 10)
    private String pass2;

    @NotEmpty(message = "이메일은 필수 입력 사항입니다.")
    @Email
    private String email;



}
