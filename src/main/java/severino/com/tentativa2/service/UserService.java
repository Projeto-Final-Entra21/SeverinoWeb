package severino.com.tentativa2.service;

import severino.com.tentativa2.dto.RegistrationDto;
import severino.com.tentativa2.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
