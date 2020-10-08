package productService.data;

import org.springframework.data.jpa.repository.JpaRepository;
import productService.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
