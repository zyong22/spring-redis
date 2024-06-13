package shop.mtcoding.bank.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO){
        User sessionUser = userService.로그인(reqDTO);
        if(sessionUser == null){
            throw new RuntimeException("아이디 혹은 패스워드가 틀렸습니다");
        }else{
            session.setAttribute("sessionUser", sessionUser);
            return "redirect:/home";
        }
    }


    @PostMapping("/join") // 회원가입
    public String join(UserRequest.JoinDTO reqDTO){
        userService.회원가입(reqDTO);
        return "redirect:/login-form";
    }


    @GetMapping("/join-form")
    public String joinForm(){
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm(){
        return "user/login-form";
    }
}
