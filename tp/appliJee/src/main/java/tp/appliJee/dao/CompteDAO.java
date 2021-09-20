package tp.appliJee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliJee.entity.Compte;

public interface CompteDAO  extends JpaRepository<Compte,Long> {
	/*
	Optional<Compte> findById(Long num);
	List<Compte> findAll();
	
	void deleteById(Long num);
	
	Compte save(Compte cpt); //saveOrUpdate , return entity with auto_incr pk
	
	//principales méthodes maintenant héritées de JpaRepository
    */
	
	//on peut coder un NamedQuery(name="Compte.findSelonSoldeMini" , ...) pour préciser
	//l'implémentation de cette requete
	List<Compte> findSelonSoldeMini(double soldeMini);
	
	//meme fonctionalité de findSelonSoldeMini mais en respectant des conventions
	//de nommage des méthodes de SpringData
	//avantage = pas besoin de coder la requête , tout est automatique (code prédéfini)
	List<Compte> findBySoldeGreaterThanEqual(double soldeMini);
}
