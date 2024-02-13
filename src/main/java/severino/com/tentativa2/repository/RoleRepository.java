package severino.com.tentativa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import severino.com.tentativa2.models.Role;
import severino.com.tentativa2.models.UserEntity;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
