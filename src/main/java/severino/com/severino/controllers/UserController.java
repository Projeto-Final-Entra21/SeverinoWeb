package severino.com.severino.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import severino.com.severino.repository.UserRepository;
import severino.com.severino.uploadingFiles.Storage.StorageService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final StorageService storageService;
    private final UserRepository userRepository;

    @GetMapping("/account")
    public String getAccountPage(Model model){
       model.addAttribute("email",SecurityContextHolder.getContext().getAuthentication().getName());
       return "account";
    }

    @PostMapping("/add/update-photo")
    public String addProfilePhoto(@RequestPart("photo") MultipartFile photo) {

        var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        var user = userRepository.findByEmail(userEmail);

        if (user == null) {
            return "redirect:/login";
        }

        storageService.store(photo);

        user.setPhoto(photo.getOriginalFilename());

        userRepository.save(user);

        return "redirect:/account";

    }
}