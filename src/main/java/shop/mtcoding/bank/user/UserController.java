package shop.mtcoding.bank.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.bank.config.RedisConfig;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession session;
    private final UserService userService;
    private final RedisConfig redisConfig;

    @GetMapping("/home")
    public String home(){
        System.out.println(redisConfig.getRedisHost());
        return "home";
    }

    // 리스폰스 바디 앞에 붙이면 문자 그대로 반환
    @GetMapping("/redis/test")
    public @ResponseBody String redisTest(){
        User sessionUser = (User)session.getAttribute("sessionUser");
        System.out.println("username: " + sessionUser.getUsername());
        return "redis test";
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
