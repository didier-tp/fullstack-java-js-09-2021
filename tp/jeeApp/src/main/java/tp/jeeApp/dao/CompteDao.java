package tp.jeeApp.dao;

import java.util.List;
import java.util.Optional;

import tp.jeeApp.entity.Compte;

//interface DAO avec méthodes CRUD (de memes noms que SpringData)

public interface CompteDao {
	Compte save(Compte entity); //saveOrUpdate
	Optional<Compte> findById(Long numCpt);
	//ou bien Compte findById(Long numCpt); si pas de spring ou jdk 1.7 ou 1.6
	void deleteById(Long numCpt);
	List<Compte> findAll();
	List<Compte> findComptesByClient(Long numClient);
	//...
}
