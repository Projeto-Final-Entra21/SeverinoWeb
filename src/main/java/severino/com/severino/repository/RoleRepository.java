    package severino.com.severino.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import severino.com.severino.models.Role;

    import java.util.List;

    public interface RoleRepository extends JpaRepository<Role, Long> {
        Role findByName(String name);

        List<Role> findByNameContaining(String filter);
    }
