package tp.jeeApp.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.jeeApp.dao.ClientDao;
import tp.jeeApp.entity.Client;
import tp.jeeApp.entity.Compte;
import tp.jeeApp.service.CompteService;

@Component
@Profile("initByJava")  //ce code n'est activé qu'avec le profil "initByJava"
                 //à sélectionner au démarrage de l'appli
public class InitDataSet {

	@Autowired
	private CompteService compteService;
	
	@Autowired
	private ClientDao clientDao;
	
	@PostConstruct
	void init() {
		Client client1 = clientDao.save(new Client(null,"alex","Therieur"));
		Client client2 = clientDao.save(new Client(null,"jean","Bon"));
		
		Compte cptA = new Compte(null,"compteA",100.0);
		cptA.setProprietaire(client1);
		compteService.sauvegarderCompte(cptA);
		Compte cptB =new Compte(null,"compteB",50.0);
		cptB.setProprietaire(client1);
		compteService.sauvegarderCompte(cptB);
		
		Compte cptC =  new Compte(null,"compteC",60.0);
		cptC.setProprietaire(client2);
		compteService.sauvegarderCompte(cptC);
	}
	
}
