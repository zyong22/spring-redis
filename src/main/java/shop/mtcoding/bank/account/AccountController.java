package shop.mtcoding.bank.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// 스프링이 관리하는 객체가 됨
@Controller // 컴퍼넌트 스캔 (shop.mtcoding.bank 패키지 이하)
public class AccountController {

    //@RequestMapping(method = RequestMethod.GET, value = "/home")
    @GetMapping("/account/list")
    public String accountList(){
        return "account/list"; // templates/list.mustache 파일을 읽어서 응답
    }

    @GetMapping("/account/save-form")
    public String accountSaveForm(){
        return "account/save-form";
    }

    @GetMapping("/account/transfer-form")
    public String accountTransferForm(){
        return "account/transfer-form";
    }

    @GetMapping("/account/1111")
    public String detail(){
        return "account/detail";
    }
}
