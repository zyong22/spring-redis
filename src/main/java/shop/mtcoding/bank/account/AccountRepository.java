package shop.mtcoding.bank.account;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AccountRepository {
    private final EntityManager em;

    public void save(Account account){
        em.persist(account);
    }
}
