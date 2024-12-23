package ex1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Login {

    @ResponseBody
    @GetMapping("/login")
    public String run(){
        return "login_ex";
    }

}
