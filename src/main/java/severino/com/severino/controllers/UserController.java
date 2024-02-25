package severino.com.severino.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import severino.com.severino.models.UserEntity;
import severino.com.severino.repository.RoleRepository;
import severino.com.severino.repository.UserRepository;
import severino.com.severino.security.SecurityService;
import severino.com.severino.service.UserService;
import severino.com.severino.storage.StorageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final StorageService storageService;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping("/account")
    public String getAccountPage(Model model) {

        Optional<UserEntity> optionalLoggedUser = securityService.getSessionUser();

        if (optionalLoggedUser.isEmpty()) {
            return "redirect:/login";
        }

        optionalLoggedUser.ifPresent(user -> model.addAttribute("user", user));


        return "account";

    }

    @PostMapping("/add/update-photo")
    public String addProfilePhoto(@RequestPart("photo") MultipartFile photo) {

        Optional<UserEntity> optionalLoggedUser = securityService.getSessionUser();

        if (optionalLoggedUser.isEmpty()) {
            return "redirect:/login";
        }

        storageService.store(photo);

        UserEntity user = optionalLoggedUser.get();
        user.setPhoto(photo.getOriginalFilename());

        userService.updateUser(user);

        return "redirect:/account";

    }


    @PostMapping("/update-user")
    public String updateUser(
            @RequestPart("photo") MultipartFile photo,
            @RequestPart("description")String description
            ) {

        Optional<UserEntity> optionalLoggedUser = securityService.getSessionUser();

        if (optionalLoggedUser.isEmpty()) {
            return "redirect:/login";
        }

        UserEntity user = optionalLoggedUser.get();
        user.setDescription(description);
        if(!photo.isEmpty()){
            storageService.store(photo);
            user.setPhoto(photo.getOriginalFilename());
        }

        userService.updateUser(user);

        return "redirect:/account";

    }
}
