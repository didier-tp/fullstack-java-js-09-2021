package tp.jeeApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_incr qui remonde en mémoire
	private Long numero;
	
	@Column(length = 48 , name = "prenom")
	private String prenom;
	private String nom;

	@OneToMany(mappedBy = "proprietaire" , fetch = FetchType.LAZY /* , 
			 cascade = { CascadeType.PERSIST , CascadeType.MERGE}*/ ) //ou bien CascadeType.ALL
	private List<Compte> comptes;
	
	
	public Client() {
		super();
	}

	public Client(Long numero, String prenom, String nom) {
		super();
		this.numero = numero;
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public void addCompte(Compte cpt) {
		if(this.comptes==null) {
			this.comptes=new ArrayList<Compte>();
		}
		this.comptes.add(cpt);
		//plus mise à jour automatique de la relation inverse:
		cpt.setProprietaire(this); //utile pour cohérence immédiate en mémoire
		                           //utile pour éventuelle cascade client --> comptes
	}

	@Override
	public String toString() {
		return "Client [numero=" + numero + ", prenom=" + prenom + ", nom=" + nom + "]";
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
	

}
