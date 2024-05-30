package shop.mtcoding.bank.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/join-form")
    public String joinForm(){
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm(){
        return "user/login-form";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/account/list"; // 페이지를 찾는게 아니라, api 주소 찾기
    }
}
