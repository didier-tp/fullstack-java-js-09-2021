package tp.appliJee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliJee.service.CompteService;

@ExtendWith(SpringExtension.class) //pour junit5/jupiter
@SpringBootTest(classes= {AppliJeeApplication.class})  //NB: AppliJeeApplication avec main , config
@ActiveProfiles("h2") //profile h2 activé au démarrage de test unitaire
public class TestCompteService {

	@Autowired
	private CompteService compteService; //à tester
	
	
	
	@Test
	public void  testRechercheCompteDuClient() {
		//...
	}
	
	//+futur test transferer
}
