package severino.com.severino.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import severino.com.severino.dto.RegistrationDto;
import severino.com.severino.models.Role;
import severino.com.severino.models.UserEntity;
import severino.com.severino.repository.RoleRepository;
import severino.com.severino.repository.UserRepository;
import severino.com.severino.service.UserService;

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
        user.setJob(registrationDto.getJob());
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    @Override
    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
