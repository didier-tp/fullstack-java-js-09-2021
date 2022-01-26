package tp.jeeApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class OrdreVirement {

	private Double montant;
	private Long numCptDeb;
	private Long numCptCred;
	
	private String message; //pour reponse
	private Boolean ok; //pour réponse
}
