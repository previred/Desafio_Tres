package cl.desafio.uf;

import cl.desafio.uf.componentes.ControlUf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UfApplication  implements CommandLineRunner{

    @Autowired
    private ControlUf control;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UfApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.control.controlUf();
    }
}
