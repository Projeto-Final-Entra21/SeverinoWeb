package severino.com.severino.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    @GetMapping("/add-services")
    public String addServices(Model model){
        return "add-services";
    }
}
