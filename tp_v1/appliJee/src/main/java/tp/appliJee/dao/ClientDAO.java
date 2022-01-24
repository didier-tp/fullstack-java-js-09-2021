package tp.appliJee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliJee.entity.Client;
import tp.appliJee.entity.Compte;

public interface ClientDAO extends JpaRepository<Client,Long> {
	//programmable via named query
	//List<Compte> findByClientNumero(long  numClient);
}
