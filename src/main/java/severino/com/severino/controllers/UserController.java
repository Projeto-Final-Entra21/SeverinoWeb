package severino.com.severino.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/account")
    public String getAccountPage(Model model){
       model.addAttribute("email",SecurityContextHolder.getContext().getAuthentication().getName());
       return "account";
    }
}