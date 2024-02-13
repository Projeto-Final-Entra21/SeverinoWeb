package severino.com.tentativa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import severino.com.tentativa2.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findFirstByUsername(String username);
}
