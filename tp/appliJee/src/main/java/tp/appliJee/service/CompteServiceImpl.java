package tp.appliJee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliJee.dao.CompteDAO;
import tp.appliJee.entity.Compte;

@Service //@Component de type "business service"
//@TransactionManagement et @TransactionAttribute d'office sur EJB
//@Transactional // à expliciter pour spring
public class CompteServiceImpl implements CompteService {
	
	//@EJB : injection de dépendances vers EJB (local ou remote)
	//@Inject : injection de dépendance via CDI (vers ejb local ou ....)
	@Autowired //injection de dépendance Spring
	private CompteDAO compteDAO;

	@Override
	public Compte rechercherCompteParNumero(long numero) {
		return compteDAO.findById(numero).orElse(null);
	}

	@Override
	public List<Compte> rechercherComptesDuClient(long numClient) {
		return compteDAO.findByDetenteurNumero(numClient);
	}

	@Override
	public Compte sauvegarderCompte(Compte cpt) {
		return compteDAO.save(cpt);
	}

	@Override
	@Transactional
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		Compte cptDeb=compteDAO.findById(numCptDeb).get();
        cptDeb.setSolde(cptDeb.getSolde() - montant);
        compteDAO.save(cptDeb); //éventuellement déclenché implicitement si @Transactional
        
        Compte cptCred=compteDAO.findById(numCptCred).get();
        cptCred.setSolde(cptCred.getSolde() + montant);
        compteDAO.save(cptCred); //éventuellement déclenché implicitement si @Transactional
	}

}
