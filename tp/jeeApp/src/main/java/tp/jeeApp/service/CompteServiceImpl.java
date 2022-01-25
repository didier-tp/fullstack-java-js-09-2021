package tp.jeeApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.jeeApp.dao.CompteDao;
import tp.jeeApp.entity.Compte;

@Service //@Component spring de type service fonctionnel
@Transactional //de spring (à expliciter)
//@TransactionManagement(CONTAINER) @TransactionAttribute(REQUIRED) par défaut sur EJB
public class CompteServiceImpl implements CompteService {
	
	//@EJB (pour injecter EJB @Local ou bien @Remote)
	//@Inject (de CDI de JEE>=6 fonctionne sur EJB @Local et avec @Named , ....)
	@Autowired //(de Spring)
	private CompteDao compteDao; //injection de dépendance

	@Override
	public Compte rechercherCompteParNum(long numCpt) {
		//return compteDao.findById(numCpt).get(); //retourne exception si compte inexistant
		return compteDao.findById(numCpt).orElse(null); //retourne null si compte inexistant
	}

	@Override
	public Compte sauvegarderCompte(Compte cpt) {
        Compte cptExistant = cpt.getNumero()==null?null:compteDao.findById(cpt.getNumero()).orElse(null);
        if(cptExistant==null)
		    return compteDao.save(cpt);
        else 
        	throw new RuntimeException("compte dejà existant");
	}

	@Override
	public Compte updateCompte(Compte cpt) {
		Compte cptExistant = cpt.getNumero()==null?null:compteDao.findById(cpt.getNumero()).orElse(null);
        if(cptExistant!=null)
		    return compteDao.save(cpt);
        else 
        	throw new RuntimeException("compte inexistant");
	}

	@Override
	public void supprimerCompte(long numCpt) {
		compteDao.deleteById(numCpt);
	}

	@Override
	public List<Compte> rechercherComptesDuClient(long numClient) {
		return compteDao.findComptesByClient(numClient);
	}

	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		try {
			Compte cptDeb = compteDao.findById(numCptDeb).get();
			cptDeb.setSolde(cptDeb.getSolde()-montant);
			//compteDao.save(cptDeb); //utile seulement pour version sans @Transactional
			
			Compte cptCred = compteDao.findById(numCptCred).get();
			cptCred.setSolde(cptCred.getSolde()+montant);
			//compteDao.save(cptCred); //utile seulement pour version sans @Transactional
		} catch (Exception e) {
			throw new RuntimeException("echec transfert");
		}
	}

}
