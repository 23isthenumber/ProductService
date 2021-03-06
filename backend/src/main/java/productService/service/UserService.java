package productService.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import productService.data.UserDao;


@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;


    public UserService(@Qualifier("alternative") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.selectUserByName(username)
                .orElseThrow(()->
                        new UsernameNotFoundException(
                                String.format("Username %s not found", username))
                );
    }
}
