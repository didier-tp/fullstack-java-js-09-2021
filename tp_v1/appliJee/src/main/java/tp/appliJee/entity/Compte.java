package tp.appliJee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name="Compte.findSelonSoldeMini",
             query = "SELECT c FROM Compte c WHERE c.solde >= ?1")
public class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	
	@Column(length = 64)
	private String label;
	
	private Double solde;
	
	@JsonIgnore()
	@ManyToOne()
	@JoinColumn(name = "detenteur")
	private Client detenteur; //+get/set
	

	public Compte(Long numero, String label, Double solde) {
		super();
		this.numero = numero;
		this.label = label;
		this.solde = solde;
	}

	public Compte() {
		super();
	}

	@Override
	public String toString() {
		return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Client getDetenteur() {
		return detenteur;
	}

	public void setDetenteur(Client detenteur) {
		this.detenteur = detenteur;
	}
	
	
	
	
}
