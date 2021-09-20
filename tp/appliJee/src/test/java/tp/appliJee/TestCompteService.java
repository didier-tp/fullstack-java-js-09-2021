package tp.appliJee;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliJee.dao.ClientDAO;
import tp.appliJee.entity.Client;
import tp.appliJee.entity.Compte;
import tp.appliJee.service.CompteService;

@ExtendWith(SpringExtension.class) //pour junit5/jupiter
@SpringBootTest(classes= {AppliJeeApplication.class})  //NB: AppliJeeApplication avec main , config
@ActiveProfiles("h2") //profile h2 activé au démarrage de test unitaire
public class TestCompteService {

	@Autowired
	private CompteService compteService; //à tester
	
	@Autowired
	private ClientDAO clientDao; //annexe au aider l'écriture du test
	
	private void initSomeData() {
		
		Client client1 = clientDao.save(new Client(null,"Therieur","alain","pwd"));
		Client client2 = clientDao.save(new Client(null,"Bon","jean","pwd"));
		
		Compte cptA = compteService.sauvegarderCompte(new Compte(null,"Compte A", 38.67));
		cptA.setDetenteur(client1);compteService.sauvegarderCompte(cptA);
		Compte cptB = compteService.sauvegarderCompte(new Compte(null,"Compte B", 34.7));
		cptB.setDetenteur(client1);compteService.sauvegarderCompte(cptB);
		
		Compte cptC = compteService.sauvegarderCompte(new Compte(null,"Compte C", 14.7));
		cptC.setDetenteur(client2);compteService.sauvegarderCompte(cptC);
		Compte cptD = compteService.sauvegarderCompte(new Compte(null,"Compte D", 4.7));
		cptD.setDetenteur(client2);compteService.sauvegarderCompte(cptD);
	}
	
	@Test
	public void  testRechercheCompteDuClient() {
		initSomeData();
		List<Compte> comptesDuClient1= compteService.rechercherComptesDuClient(1L);
		Assertions.assertTrue(comptesDuClient1.size()>=2);
		System.out.println("comptesDuClient1="+comptesDuClient1);
	}
	
	//+futur test transferer
}
