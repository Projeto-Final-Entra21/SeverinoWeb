package severino.com.severino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import severino.com.severino.models.Role;
import severino.com.severino.models.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findFirstByUsername(String username);

    List<UserEntity> findByRolesContains(Role role);
}
