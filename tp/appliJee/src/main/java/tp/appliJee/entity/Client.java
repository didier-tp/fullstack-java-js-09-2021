package tp.appliJee.entity;

import java.util.List;

public class Client {
	@Id
	//...
	private Long numero;
	private String nom;
	private String prenom;
	private String password;
	//....
	
	//@OneToMany(....)  //inverse du manyToOne
	public List<Compte> comptes; 
}
