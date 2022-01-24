package tp.appliJee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.appliJee.dao.CompteDAO;
import tp.appliJee.entity.Compte;
import tp.appliJee.exception.NotFoundException;

@Service // @Component de type "business service"
//@TransactionManagement et @TransactionAttribute d'office sur EJB
//@Transactional // à expliciter pour spring
public class CompteServiceImpl implements CompteService {

	// @EJB : injection de dépendances vers EJB (local ou remote)
	// @Inject : injection de dépendance via CDI (vers ejb local ou ....)
	@Autowired // injection de dépendance Spring
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
		Compte cptDeb = compteDAO.findById(numCptDeb).get();
		cptDeb.setSolde(cptDeb.getSolde() - montant);
		// compteDAO.save(cptDeb); //éventuellement déclenché implicitement si
		// @Transactional
		// avec jpa/hibernate les modifs en mémoire
		// sur les objets persistants sont automatiquement
		// répercutées en base lors du commit

		Compte cptCred = compteDAO.findById(numCptCred).get();
		cptCred.setSolde(cptCred.getSolde() + montant);
		// compteDAO.save(cptCred); //éventuellement déclenché implicitement si
		// @Transactional
	}

	@Override
	public List<Compte> rechercherTousLesComptes() {
		return compteDAO.findAll();
	}

	
	@Override
	public Compte updateCompte(Compte cpt) throws NotFoundException {
		if (cpt.getNumero()!=null && compteDAO.existsById(cpt.getNumero()))
			return compteDAO.save(cpt);
		else
			throw new NotFoundException("update impossible, aucun compte existe avec numero=" + cpt.getNumero());
	}
	
	@Override
	public Compte ajouterCompte(Compte cpt) {
		if (cpt.getNumero()==null || !compteDAO.existsById(cpt.getNumero()))
			return compteDAO.save(cpt);
		else
			throw new RuntimeException("ajout impossible , compte déja existant avec numero=" + cpt.getNumero());
	}

	@Override
	public void deleteCompteByNum(Long numCpt) throws NotFoundException {
		if (numCpt!=null && compteDAO.existsById(numCpt))
		   compteDAO.deleteById(numCpt);
		else
			throw new NotFoundException("aucun compte ne peur etre supprime avec numero=" + numCpt);
	}

}
