package severino.com.severino.service;

import severino.com.severino.dto.RegistrationDto;
import severino.com.severino.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
