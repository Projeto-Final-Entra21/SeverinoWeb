package severino.com.severino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import severino.com.severino.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
