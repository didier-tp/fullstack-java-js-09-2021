package tp.appliJee.service;

import java.util.List;

import tp.appliJee.entity.Compte;

public interface CompteService {
	
	Compte rechercherCompteParNumero(long numero);
	List<Compte> rechercherComptesDuClient(long numClient);
	List<Compte> rechercherTousLesComptes();
	
	
	Compte sauvegarderCompte(Compte cpt); //save or update
	
	void transferer(double montant,long numCptDeb,long numCptCred);

}
