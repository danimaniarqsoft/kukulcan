package mx.infotec.dads.kukulkan.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/uiapps")
public class UiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(UiApplication.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage() {
        LOGGER.debug("Getting the HomePage");
        return "index";
    }

}
