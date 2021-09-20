package tp.appliJee.dao;

import java.util.List;
import java.util.Optional;

import tp.appliJee.entity.Compte;

public interface CompteDAO /* extends JpaRepository */{
	
	Optional<Compte> findById(Long num);
	List<Compte> findAll();
	
	void deleteById(Long num);
	
	Compte save(Compte cpt); //saveOrUpdate , return entity with auto_incr pk
    //...
}
