package tp.appliJee.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tp.appliJee.entity.Compte;

//@Stateless ou @Standalone sur EJB
@Repository //@Component Spring de type DAO
@Transactional //en version spring pour meme comportement que EJB
public class CompteDaoJpa implements CompteDAO {
	
	@PersistenceContext()
	private EntityManager em;

	@Override
	public Optional<Compte> findById(Long num) {
		Compte cpt = em.find(Compte.class, num);
		return Optional.of(cpt); //depuis java 8
	}

	@Override
	public List<Compte> findAll() {
		return em.createQuery("SELECT c FROM Compte c",Compte.class).getResultList();
	}

	@Override
	public void deleteById(Long num) {
		Compte cpt = em.find(Compte.class, num);
        em.remove(cpt);
	}

	@Override
	public Compte save(Compte cpt) {
		if(cpt.getNumero()==null)
			em.persist(cpt); //INSERT INTO + auto_incr
		else 
			em.merge(cpt); //UPDATE
		return cpt;
	}

}
