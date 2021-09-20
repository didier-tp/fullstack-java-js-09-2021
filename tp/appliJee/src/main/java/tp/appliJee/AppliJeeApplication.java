package tp.appliJee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppliJeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliJeeApplication.class, args);
		System.out.println("http://localhost:8080/appliJee");
	}

}
