package tp.jeeApp.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tp.jeeApp.entity.Compte;

@Repository //@Repository hérite de @Component et signifie "DAO Spring"
@Transactional()// propagation =  Propagation.REQUIRED par defaut
public class CompteDaoImpl implements CompteDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Compte save(Compte compte) {
		if(compte.getNumero()==null) {
			entityManager.persist(compte);
		}
		else {
			compte = entityManager.merge(compte);
		}
		return compte;
	}

	@Override
	public Optional<Compte> findById(Long numCpt) {
		return Optional.ofNullable(entityManager.find(Compte.class, numCpt));
	}

	@Override
	public void deleteById(Long numCpt) {
		Compte cpt = entityManager.find(Compte.class, numCpt);
		if(cpt != null) {
			entityManager.remove(cpt);
		}
		else {
			throw new RuntimeException("cannot delete Compte with numCpt="+numCpt);
		}

	}

	@Override
	public List<Compte> findAll() {
		return entityManager.createQuery("SELECT c FROM Compte c",Compte.class)
				            .getResultList();
	}

	@Override
	public List<Compte> findComptesByClient(Long numClient) {
		return entityManager.createNamedQuery("Compte.findComptesByClient", Compte.class)
				.setParameter("numCli", numClient) //:numCli dans JPQL
				.getResultList();
	}

}
