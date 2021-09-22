package tp.appliJee.service;

import java.util.List;

import tp.appliJee.entity.Compte;
import tp.appliJee.exception.NotFoundException;

public interface CompteService {
	
	Compte rechercherCompteParNumero(long numero);
	List<Compte> rechercherComptesDuClient(long numClient);
	List<Compte> rechercherTousLesComptes();
	
	
	Compte sauvegarderCompte(Compte cpt); //save or update
	Compte ajouterCompte(Compte cpt); //insert into
	Compte updateCompte(Compte cpt) throws NotFoundException; //update
	
	void deleteCompteByNum(Long numCpt) throws NotFoundException; 
	
	void transferer(double montant,long numCptDeb,long numCptCred);

}
