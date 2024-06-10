package shop.mtcoding.bank.account;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shop.mtcoding.bank.user.User;


// 스프링이 관리하는 객체가 됨
@RequiredArgsConstructor
@Controller // 컴퍼넌트 스캔 (shop.mtcoding.bank 패키지 이하)
public class AccountController {

    private final HttpSession session;

    //@RequestMapping(method = RequestMethod.GET, value = "/home")
    @GetMapping("/account/list")
    public String accountList(){
        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다.");

        return "account/list"; // templates/list.mustache 파일을 읽어서 응답
    }

    @GetMapping("/account/save-form")
    public String accountSaveForm(){
        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다.");

        return "account/save-form";
    }

    @GetMapping("/account/transfer-form")
    public String accountTransferForm(){
        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다.");

        return "account/transfer-form";
    }

    @GetMapping("/account/1111")
    public String detail(){
        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null) throw new RuntimeException("인증되지 않은 사용자입니다.");

        return "account/detail";
    }
}
