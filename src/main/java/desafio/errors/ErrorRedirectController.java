package desafio.errors;
import desafio.enumerators.ConstantesStr;
import desafio.enumerators.ConstantesMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class ErrorRedirectController {

    private final Logger logger = Logger.getLogger(ErrorRedirectController.class.getName());

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect(HttpServletRequest request) {
        String url = request.getRequestURI();
        this.logger.severe(url + ": (" + ConstantesMsg.ACCESS_DENIED.toString() + ")");
        this.logger.info(ConstantesMsg.ACCESS_REDIRECTED.toString() + ConstantesStr.INIT_URL.toString());
        return "redirect:" + ConstantesStr.INIT_URL.toString();
    }

}
