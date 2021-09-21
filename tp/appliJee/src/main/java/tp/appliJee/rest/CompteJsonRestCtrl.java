package tp.appliJee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliJee.entity.Compte;
import tp.appliJee.service.CompteService;

@RestController
@RequestMapping(value="/rest/compte" , headers="Accept=application/json")
public class CompteJsonRestCtrl {
	
	@Autowired //ou @Inject
	private CompteService compteService; //internal business service or DAO
	
	
	//methode qui retourne un seul compte de numero précisé
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: .../appliJee/rest/compte/1
	@GetMapping(value="/{numCpt}" )
	public Compte getDeviseByName(@PathVariable("numCpt") Long numeroCpt) {
	    return compteService.rechercherCompteParNumero(numeroCpt);
	}
	
	//méthode qui retourne plusieurs comptes (critere de recherche "numClient")
	@GetMapping(value="")
	public List<Compte> getComptesByCriteria(@RequestParam(value="numClient",required=false) Long numClient) {
		 if(numClient!=null)
	         return compteService.rechercherComptesDuClient(numClient);
		 else 
			 return compteService.rechercherTousLesComptes();
	}
}
