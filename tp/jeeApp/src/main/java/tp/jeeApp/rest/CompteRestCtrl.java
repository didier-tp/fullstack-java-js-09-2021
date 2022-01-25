package tp.jeeApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tp.jeeApp.entity.Compte;
import tp.jeeApp.service.CompteService;

@RestController //composant spring de type crontroleur pour WS REST
@RequestMapping(value="/bank-api/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	
	@Autowired //ou @Inject
	private CompteService compteService;
	//internal business service or DAO
	
	
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte/1
	@RequestMapping(value="/{numCompte}" , method=RequestMethod.GET)
	public Compte getCompteByNum(@PathVariable("numCompte") Long numCpt) {
	     return compteService.rechercherCompteParNum(numCpt);
	}

}
