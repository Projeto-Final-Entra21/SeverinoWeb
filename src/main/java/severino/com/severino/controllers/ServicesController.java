package severino.com.severino.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import severino.com.severino.models.Role;
import severino.com.severino.models.UserEntity;
import severino.com.severino.repository.RoleRepository;
import severino.com.severino.security.SecurityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ServicesController {
    private final SecurityService securityService;
    private final RoleRepository roleRepository;

    @GetMapping("/add-services")
    public String addServices(Model model){
        return "add-services";
    }

    @PostMapping("/filter-services")
    public String addServices(Model model, @RequestPart("filter") String filter ){
        Optional<UserEntity> optionalLoggedUser = securityService.getSessionUser();

        if (optionalLoggedUser.isEmpty()) {
            return "redirect:/login";
        }
        List<Role> profissoes = roleRepository.findAll();
        List<Role> filtrados = profissoes.stream()
                .filter(profissao -> profissao.getName().toLowerCase().contains(filter.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("roles", filtrados);
        return "find-services";
    }

    @GetMapping("/find-services")
    public String getFindService(Model model) {
        Optional<UserEntity> optionalLoggedUser = securityService.getSessionUser();

        if (optionalLoggedUser.isEmpty()) {
            return "redirect:/login";
        }

        model.addAttribute("roles", roleRepository.findAll());
        return "find-services";
    }
}
