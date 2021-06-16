package desafio;

import desafio.dao.Desafio;
import desafio.enumerators.ConstantesStr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.logging.Logger;

@SpringBootApplication
@EnableSwagger2
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String... args) {
        SpringApplication.run(Main.class, args);
        openHomePage();
    }

    private static void openHomePage() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(ConstantesStr.COMMAND_OPEN_CHROME.toString());
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
    }
}
