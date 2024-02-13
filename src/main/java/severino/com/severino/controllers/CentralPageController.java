package severino.com.severino.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CentralPageController {
    @GetMapping("/centralPage")
        public String getCentralPage(){
            return "centralPage";
        }

}
