package tp.jeeApp.service;

import java.util.List;

import tp.jeeApp.entity.Compte;

//avec throws RuntimeException implicites .
public interface CompteService {
	Compte rechercherCompteParNum(long numCpt);
	Compte sauvegarderCompte(Compte cpt); //en retour Compte avec numero auto_incr
	Compte updateCompte(Compte cpt);
	void  supprimerCompte(long numCpt);
	List<Compte> rechercherComptesDuClient(long numClient);
    void transferer(double montant,long numCptDeb,long numCptCred);
}
