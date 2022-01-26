package tp.jeeApp.dto;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class CompteDto {

	private Long numero;
	private String label;
	
	@Min(value = 0 , message = "le solde du compte doit etre positif")
	private Double solde;
	//pas plus

}
