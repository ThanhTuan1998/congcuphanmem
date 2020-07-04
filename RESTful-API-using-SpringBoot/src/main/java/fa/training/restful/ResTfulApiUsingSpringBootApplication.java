package fa.training.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("/home")
public class ResTfulApiUsingSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResTfulApiUsingSpringBootApplication.class, args);
	}

}
