package severino.com.severino.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import severino.com.severino.models.UserEntity;
import severino.com.severino.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public Optional<UserEntity> getSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            return Optional.ofNullable(userRepository.findByEmail(userEmail));
        }

        return Optional.empty();
    }

}
