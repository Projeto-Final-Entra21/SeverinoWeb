package severino.com.tentativa2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import severino.com.tentativa2.dto.RegistrationDto;
import severino.com.tentativa2.models.Role;
import severino.com.tentativa2.models.UserEntity;
import severino.com.tentativa2.repository.RoleRepository;
import severino.com.tentativa2.repository.UserRepository;
import severino.com.tentativa2.service.UserService;

import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setUF(registrationDto.getUF());
        user.setCPF(registrationDto.getCPF());
        user.setCity(registrationDto.getCity());
        user.setBirthday(registrationDto.getBirthday());
        user.setWorker((registrationDto.getWorker()));
        user.setGender(registrationDto.getGender());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    @Override
    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
