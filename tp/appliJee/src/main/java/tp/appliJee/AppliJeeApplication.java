package tp.appliJee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppliJeeApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AppliJeeApplication.class, args);
		SpringApplication app = new SpringApplication(AppliJeeApplication.class);
		//app.setAdditionalProfiles("p1","p2","p3");
		ConfigurableApplicationContext context = app.run(args);
		
		System.out.println("http://localhost:8080/appliJee");
	}

}
