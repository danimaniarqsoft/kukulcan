package mx.infotec.dads.kukulkan.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/uiapps")
public class UiApplication {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage() {
        return "index";
    }

}
