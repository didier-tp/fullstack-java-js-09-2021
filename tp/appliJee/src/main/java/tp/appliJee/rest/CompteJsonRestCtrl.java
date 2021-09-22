package tp.appliJee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliJee.entity.Compte;
import tp.appliJee.exception.NotFoundException;
import tp.appliJee.service.CompteService;

@RestController
//la partie de l'URL "bank-api" designe une API REST (paquet de web services REST lié à un domaine
//et gérer par une même application) . Utile si aiguillage au niveau d'un éventuel serveur intermédiaire
@RequestMapping(value="/bank-api/compte" , headers="Accept=application/json")
public class CompteJsonRestCtrl {
	
	@Autowired //ou @Inject
	private CompteService compteService; //internal business service or DAO
	
	
	//methode qui retourne un seul compte de numero précisé
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: .../appliJee/bank-api/compte/1
	/*
	@GetMapping(value="/{numCpt}" )
	public Compte getCompteByNumero(@PathVariable("numCpt") Long numeroCpt) {
	    return compteService.rechercherCompteParNumero(numeroCpt);
	}
	*/
	
	/*
	@GetMapping(value="/{numCpt}" ) 
	public ResponseEntity<?> getCompteByNumero(@PathVariable("numCpt") Long numeroCpt) {
	    Compte cpt = compteService.rechercherCompteParNumero(numeroCpt);
	    if(cpt!=null) {
	    	return new ResponseEntity<Compte>(cpt,HttpStatus.OK);
	    }
	    else {
	    	Map<String,Object> mapError = new HashMap<>();
	    	mapError.put("message","compte pas trouve");
	    	mapError.put("numero de compte pas trouve",numeroCpt);
	    	//return new ResponseEntity<String>( "{ \"message\" : \"compte pas trouve\" }",HttpStatus.NOT_FOUND);//404: NOT_FOUND
	    	return new ResponseEntity<Map>( mapError,HttpStatus.NOT_FOUND);//404: NOT_FOUND
	    }
	}
	*/
	
	@GetMapping(value="/{numCpt}" ) 
	public Compte getCompteByNumero(@PathVariable("numCpt") Long numeroCpt) {
		Compte cpt=null;
		try {
	        cpt = compteService.rechercherCompteParNumero(numeroCpt);
		}catch(Exception ex) {
			throw new NotFoundException("compte pas trouve  " , ex );
		}
	    if(cpt==null) 
	    	throw new NotFoundException("compte pas trouve pour numero " + numeroCpt );
	    else
	        return cpt;
	}
	
	//méthode qui retourne plusieurs comptes (critere de recherche "numClient")
	//URL de déclenchement: .../appliJee/bank-api/compte  ou bien .../appliJee/bank-api/compte?numClient=1
	@GetMapping(value="")
	public List<Compte> getComptesByCriteria(@RequestParam(value="numClient",required=false) Long numClient) {
		 if(numClient!=null)
	         return compteService.rechercherComptesDuClient(numClient);
		 else 
			 return compteService.rechercherTousLesComptes();
	}
	
	//URL de déclenchement: http://localhost:8080/appliJee/bank-api/compte
	//à appler en mode POST avec dans la partie body 
	//{ "numero" : null , "label" : "compteXy" , "solde" : 60.0 }
	//ou bien { "label" : "compteXy" , "solde" : 60.0 }
	@PostMapping(value="" )
	Compte postNewCompte(@RequestBody Compte nouveauCompte) {
	System.out.println("nouveau compte:" + nouveauCompte);
	   compteService.sauvegarderCompte(nouveauCompte);
	return nouveauCompte; //avec .numero auto incrémenté
	}
	
	
}
