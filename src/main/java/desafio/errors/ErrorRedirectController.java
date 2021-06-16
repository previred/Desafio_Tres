package desafio.errors;
import desafio.enumerators.ConstantesStr;
import desafio.enumerators.ConstantesMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class ErrorRedirectController {

    private final Logger logger = Logger.getLogger(ErrorRedirectController.class.getName());

    @GetMapping(value = "/{[path:[^\\.]*}")
    public String redirect(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (!url.equalsIgnoreCase(ConstantesStr.VALUES_URL.toString())) {
            String info = new StringBuilder(url + ": (" + ConstantesMsg.ACCESS_DENIED.toString() + ")").toString();
            this.logger.severe(info);
            info = new StringBuilder(ConstantesMsg.ACCESS_REDIRECTED.toString() + ConstantesStr.INIT_URL.toString()).toString();
            this.logger.info(info);
            return "redirect:" + ConstantesStr.INIT_URL.toString();
        }
        return "redirect:" + ConstantesStr.VALUES_URL.toString();
    }
}
