package tp.jeeApp.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.jeeApp.dto.CompteDto;
import tp.jeeApp.dto.Erreur;
import tp.jeeApp.dto.OrdreVirement;
import tp.jeeApp.entity.Compte;
import tp.jeeApp.exception.NotFoundException;
import tp.jeeApp.service.CompteService;

@RestController //composant spring de type crontroleur pour WS REST **
@RequestMapping(value="/bank-api/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired //ou @Inject
	private CompteService compteService;//internal business service or DAO
	
	//RECHERCHE UNIQUE selon RESOURCE-ID:
	//URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte/1
	/*
	@GetMapping(value="/{numCompte}" )
	public ResponseEntity<?> getCompteByNum(@PathVariable("numCompte") Long numCpt) {
	     Compte compte = compteService.rechercherCompteParNum(numCpt);
	     if(compte != null)
	    	 return new ResponseEntity<Compte>(compte,HttpStatus.OK);
	     else {
	    	
	    	 //Map<String,Object> mapError = new HashMap<>();
	    	 //mapError.put("message", "compte num="+numCpt + " not found");
	    	 //mapError.put("details", "blabla");
	    	 //return new ResponseEntity<Map>(mapError,HttpStatus.NOT_FOUND);
	    	
	    	 return new ResponseEntity<Erreur>(
	    			 new Erreur("compte num="+numCpt + " not found","details ..."),
	    			 HttpStatus.NOT_FOUND);
	     }
	}
	*/
	
	//URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte/1
	@GetMapping(value="/{numCompte}" )
	public CompteDto getCompteByNum(@PathVariable("numCompte") Long numCpt) {
	    Compte compte = compteService.rechercherCompteParNum(numCpt);
		if(compte ==null) {
			throw new NotFoundException("compte pas trouve pour numero="+numCpt);
		}else
			return new CompteDto(compte.getNumero(),compte.getLabel(),compte.getSolde());
		
	}
	
	//DELETE , URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte/4
	@DeleteMapping(value="/{numCompte}" )
	public ResponseEntity<?> deleteCompteByNum(@PathVariable("numCompte") Long numCpt) {
		    try {
				compteService.supprimerCompte(numCpt);
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);//204 (suppression réussie)
			} catch (Exception e) {
				return new ResponseEntity<Erreur>(
						new Erreur("echec suppression", "compte num="+numCpt),
						HttpStatus.NOT_FOUND);//ou INTERNAL_SERVER_ERROR
			}
			
			
		}
	
	//RECHERCHE MULTIPLE :
	//URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte
	//ou bien http://localhost:8080/jeeApp/bank-api/compte?numClient=1
	@GetMapping(value="")
	//valeur de retour idéale sur vrai projet (pas simple tp):
	//List<CompteDto>
	public List<Compte> getComptesByCriteria(
			@RequestParam(value="numClient",required=false)	Long numClient) {
	if(numClient==null)
		return compteService.rechercherTousLesComptes();
	else
		return  compteService.rechercherComptesDuClient(numClient);
	}
	
	//URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte/virement
	//avec dans la partie body de la requete :
	// {  "montant" : 50 , "numCptDeb" : 1 , "numCptCred" : 2 }
	@PostMapping(value="virement")
	public ResponseEntity<OrdreVirement> postOrdreVirement(@Validated @RequestBody  OrdreVirement ordreVirement) {
		try {
			compteService.transferer(ordreVirement.getMontant(), 
					                 ordreVirement.getNumCptDeb(), 
					                 ordreVirement.getNumCptCred());
			ordreVirement.setOk(true); ordreVirement.setMessage("virement bien effectue");
			return new ResponseEntity<OrdreVirement>(ordreVirement,HttpStatus.OK);
		} catch (Exception e) {
			ordreVirement.setOk(false); ordreVirement.setMessage(e.getMessage());
			return new ResponseEntity<OrdreVirement>(ordreVirement,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//POST, URL de déclenchement: http://localhost:8080/jeeApp/bank-api/compte
	//avec dans la partie body de la requete :
	// {  "label" : "compte Xy" , "solde" : 200.0 }
	//ou bien {  "numero" : null, "label" : "compte Xy" , "solde" : 200.0 }
	@PostMapping(value="")
	public ResponseEntity<?> postCompte(@Valid @RequestBody  CompteDto compteDto) {
			try {
				Compte compte = new Compte(compteDto.getNumero(), 
						compteDto.getLabel(),
						compteDto.getSolde());
				compte = compteService.sauvegarderCompte(compte);
			    compteDto.setNumero(compte.getNumero()); 
				return new ResponseEntity<CompteDto>(compteDto,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Erreur>(
						new Erreur("echec sauvegarde compte",e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}


}
