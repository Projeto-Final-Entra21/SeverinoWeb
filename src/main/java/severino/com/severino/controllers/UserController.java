package severino.com.severino.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import severino.com.severino.models.UserEntity;
import severino.com.severino.repository.UserRepository;
import severino.com.severino.security.SecurityService;
import severino.com.severino.service.UserService;
import severino.com.severino.uploadingFiles.Storage.StorageService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final StorageService storageService;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final UserService userService;

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
}
