package tp.appliJee.init;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.appliJee.dao.ClientDAO;
import tp.appliJee.entity.Client;
import tp.appliJee.entity.Compte;
import tp.appliJee.service.CompteService;

@Component 
@Profile("reinit_dev") //profile à activer en phase de dev , mais pas production
public class InitDataSetV2 {
	
	//injection de dépendances par constructeur 
	public InitDataSetV2(ClientDAO clientDao,CompteService compteService){
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

}
