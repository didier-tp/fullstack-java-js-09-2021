package tp.jeeApp.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.jeeApp.entity.Compte;
import tp.jeeApp.service.CompteService;

@Component
@Profile("dev")  //ce code n'est activé qu'avec le profil "dev"
                 //à sélectionner au démarrage de l'appli
public class InitDataSet {

	@Autowired
	private CompteService compteService;
	
	@PostConstruct
	void init() {
		compteService.sauvegarderCompte(new Compte(null,"compteA",100.0));
		compteService.sauvegarderCompte(new Compte(null,"compteB",50.0));
		compteService.sauvegarderCompte(new Compte(null,"compteC",60.0));
	}
	
}
