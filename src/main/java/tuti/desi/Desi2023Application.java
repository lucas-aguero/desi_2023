package tuti.desi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("tuti.desi")
@ComponentScan(basePackages = {"tuti.desi.util.inicializador"})
public class Desi2023Application {

	public static void main(String[] args) {
		SpringApplication.run(Desi2023Application.class, args);
	}

}
