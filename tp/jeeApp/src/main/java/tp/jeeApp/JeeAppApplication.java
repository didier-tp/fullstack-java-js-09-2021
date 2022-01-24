package tp.jeeApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JeeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeeAppApplication.class, args);
		System.out.println("http://localhost:8080/jeeApp/index.html");
	}

}
