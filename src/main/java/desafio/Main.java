package desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
public class Main {

    public static void main(String... args) {
        SpringApplication.run(Main.class, args);
        openHomePage();
    }

    private static void openHomePage() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("cmd /c start chrome.exe http://localhost:8010/swagger-ui.html");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
