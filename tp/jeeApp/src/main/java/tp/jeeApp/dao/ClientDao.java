package tp.jeeApp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tp.jeeApp.entity.Client;

public interface ClientDao extends CrudRepository<Client, Long>  {
     //on hérite automatiquement de :
	//.save() , .findById() , .deleteById() , .findAll()
	List<Client> findByPrenomAndNom(String prenom,String nom);
}
