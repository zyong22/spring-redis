package shop.mtcoding.bank.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public void save(String username, String password, String email, String fullname){
        Query query =
                em.createNativeQuery("insert into user_tb(username, password, email, fullname) values(?,?,?,?)");
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, email);
        query.setParameter(4, fullname);

        query.executeUpdate(); // write (insert, delete, update)
    }

    public User findByUsernameAndPassword(String username, String password){
        Query query =
                em.createNativeQuery("select * from user_tb where username=? and password=?");
        query.setParameter(1, username);
        query.setParameter(2, password);

        try {
            Object[] obs = (Object[]) query.getSingleResult();
            // [0] -> id, [1] -> username, [2] -> password, [3] -> email, [4] -> fullname

            User user = new User();
            user.setId((Integer) obs[0]);
            user.setPassword((String) obs[1]);
            user.setEmail((String) obs[2]);
            user.setFullname((String) obs[3]);
            user.setUsername((String) obs[4]);

            return user;
        }catch (Exception e){
            return null;
        }
    }

    public User findByUsername(String username){
        // JPQL
        Query query =
                em.createQuery("select u from User u where u.username=:username", User.class);
        query.setParameter("username", username);

        try {
            User user = (User) query.getSingleResult();
            return user;
        }catch (Exception e){
            return null;
        }
    }
}
