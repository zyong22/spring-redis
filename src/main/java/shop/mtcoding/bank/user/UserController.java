package shop.mtcoding.bank.user;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession session;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public String login(String username, String password){
        User sessionUser = userRepository.findByUsernameAndPassword(username, password);
        if(sessionUser == null){
            throw new RuntimeException("아이디 혹은 패스워드가 틀렸습니다");
        }else{
            session.setAttribute("sessionUser", sessionUser);
            return "redirect:/account/list";
        }
    }

    @GetMapping("/test1")
    public void test1(HttpServletResponse response) throws IOException {
        //response.sendRedirect("/login-form");
        response.setHeader("Location", "http://localhost:8080/login-form");
        response.setStatus(302);
    }

    // http3.0
    // http1.1 - 사용중 Post, Get, Put, Delete
    // http1.0 - Post(insert, delete, update), Get(select)
    // Post(insert), Get(select)
    @PostMapping("/join") // 회원가입
    public String join(String username, String password, String email, String fullname){
        System.out.println("username : "+username);
        System.out.println("password : "+password);
        System.out.println("email : "+email);
        System.out.println("fullname : "+fullname);

        userRepository.save(username, password, email, fullname);

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

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/account/list"; // 페이지를 찾는게 아니라, api 주소 찾기
    }
}
