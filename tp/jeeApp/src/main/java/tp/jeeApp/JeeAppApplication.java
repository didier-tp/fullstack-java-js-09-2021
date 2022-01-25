package tp.jeeApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JeeAppApplication {

	public static void main(String[] args) {
		//SpringApplication.run(JeeAppApplication.class, args);
		SpringApplication app = new SpringApplication(JeeAppApplication.class);
		app.setAdditionalProfiles("dev","initByJava","profile3");
		//app.setAdditionalProfiles("dev","loadSql","profile3");
		app.run(args);
		
		System.out.println("http://localhost:8080/jeeApp/index.html");
	}

}
