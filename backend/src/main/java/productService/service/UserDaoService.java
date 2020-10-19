package productService.service;

import org.assertj.core.util.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import productService.data.UserDao;
import productService.model.User;

import java.util.List;
import java.util.Optional;

@Repository("alternative")
public class UserDaoService implements UserDao {

    private final PasswordEncoder passwordEncoder;

    public UserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> selectUserByName(String username) {
        return getUser()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<User> getUser(){
        List <User> user = Lists.newArrayList(
                new User(
                        "user1",
                        passwordEncoder.encode("user123"),
                        true,
                        true,
                        true,
                        true
                ));
        return user;
    }

}
