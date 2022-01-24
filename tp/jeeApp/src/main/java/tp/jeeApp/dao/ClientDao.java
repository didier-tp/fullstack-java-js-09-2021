package tp.jeeApp.dao;

import org.springframework.data.repository.CrudRepository;

import tp.jeeApp.entity.Client;

public interface ClientDao extends CrudRepository<Client, Long>  {
     //on hérite automatiquement de :
	//.save() , .findById() , .deleteById() , .findAll()
	
}
