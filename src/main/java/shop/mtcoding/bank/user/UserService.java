package shop.mtcoding.bank.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 비지니스 로직 처리, 트랜잭션 처리
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(String username, String password, String email, String fullname){

        // STEP: 두번째 단계
        User user = userRepository.findByUsername(username);
        if(user != null) throw new RuntimeException("유저네임 중복되었습니다 : "+username);

        userRepository.save(username, password, email, fullname);
    }

    public User 로그인(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
