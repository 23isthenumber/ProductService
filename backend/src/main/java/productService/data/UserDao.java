package productService.data;

import productService.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> selectUserByName(String username);

}
