package tp.appliJee.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	/*
	@GetMapping(value="/{numCpt}" )
	public Compte getCompteByNumero(@PathVariable("numCpt") Long numeroCpt) {
	    return compteService.rechercherCompteParNumero(numeroCpt);
	}
	*/
	
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
	
	//méthode qui retourne plusieurs comptes (critere de recherche "numClient")
	//URL de déclenchement: .../appliJee/rest/compte  ou bien .../appliJee/rest/compte?numClient=1
	@GetMapping(value="")
	public List<Compte> getComptesByCriteria(@RequestParam(value="numClient",required=false) Long numClient) {
		 if(numClient!=null)
	         return compteService.rechercherComptesDuClient(numClient);
		 else 
			 return compteService.rechercherTousLesComptes();
	}
}
