package severino.com.severino.service;

import severino.com.severino.dto.RegistrationDto;
import severino.com.severino.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    void updateUser(UserEntity user);

    UserEntity findByUsername(String username);
}
