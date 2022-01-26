package tp.jeeApp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.jeeApp.dto.Erreur;
import tp.jeeApp.entity.Compte;
import tp.jeeApp.service.CompteService;

@RestController //composant spring de type crontroleur pour WS REST **
@RequestMapping(value="/bank-api/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired //ou @Inject
	private CompteService compteService;//internal business service or DAO
	
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte/1
	@GetMapping(value="/{numCompte}" )
	public ResponseEntity<?> getCompteByNum(@PathVariable("numCompte") Long numCpt) {
	     Compte compte = compteService.rechercherCompteParNum(numCpt);
	     if(compte != null)
	    	 return new ResponseEntity<Compte>(compte,HttpStatus.OK);
	     else {
	    	 /*
	    	 Map<String,Object> mapError = new HashMap<>();
	    	 mapError.put("message", "compte num="+numCpt + " not found");
	    	 mapError.put("details", "blabla");
	    	 return new ResponseEntity<Map>(mapError,HttpStatus.NOT_FOUND);
	    	 */
	    	 return new ResponseEntity<Erreur>(
	    			 new Erreur("compte num="+numCpt + " not found","details ..."),
	    			 HttpStatus.NOT_FOUND);
	     }
	}
	
	//RECHERCHE MULTIPLE :
	//URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte
	//ou bien http://localhost:8080/jeeApp/bank-api/compte?numClient=1
	@GetMapping(value="")
	public List<Compte> getComptesByCriteria(
			@RequestParam(value="numClient",required=false)	Long numClient) {
	if(numClient==null)
		return compteService.rechercherTousLesComptes();
	else
		return  compteService.rechercherComptesDuClient(numClient);
	}

}