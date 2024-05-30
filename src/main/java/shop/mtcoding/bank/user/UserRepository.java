package shop.mtcoding.bank.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // ioc 등록
public class UserRepository {
    
    @Autowired // 의존성 주입
    private EntityManager em;

    @Transactional
    public void save(String username, String password, String email, String fullname){
        Query query =
                em.createNativeQuery("insert into user_tb(username, password, email, fullname) values(?,?,?,?)");
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, email);
        query.setParameter(4, fullname);

        query.executeUpdate();
    }
}
