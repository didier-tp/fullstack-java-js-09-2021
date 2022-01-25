package tp.jeeApp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.jeeApp.JeeAppApplication;
import tp.jeeApp.entity.Compte;

@ExtendWith(SpringExtension.class) //pour junit 5 , @RunWith(SpringRunner.class) si junit 4
@SpringBootTest(classes= {JeeAppApplication.class})
public class TestCompteService {
	
	@Autowired
	private CompteService compteService;
	
	@Test
	public void testBonTransfert() {
		Compte cptA  = compteService.sauvegarderCompte(new Compte(null,"compteA",100.0));
		Compte cptB  = compteService.sauvegarderCompte(new Compte(null,"compteB",60.0));
		compteService.transferer(25.0, cptA.getNumero(), cptB.getNumero());
		Compte cptA_apres = compteService.rechercherCompteParNum(cptA.getNumero());
		Compte cptB_apres = compteService.rechercherCompteParNum(cptB.getNumero());
		Assertions.assertEquals(cptA_apres.getSolde(), cptA.getSolde()-25,0.0001);
		Assertions.assertEquals(cptB_apres.getSolde(), cptB.getSolde()+25,0.0001);
		System.out.println("avant virement : " + cptA.getSolde() + " " + cptB.getSolde());
		System.out.println("apres virement : " + cptA_apres.getSolde() + " " + cptB_apres.getSolde());
	}
	
	@Test
	public void testMauvaisTransfert() {
		Compte cptA  = compteService.sauvegarderCompte(new Compte(null,"compteA",100.0));
		Compte cptB  = compteService.sauvegarderCompte(new Compte(null,"compteB",60.0));
		try {
			compteService.transferer(25.0, cptA.getNumero(), 678 /* inexistant */);
		} catch (Exception e) {
			System.err.println("exception normale : " + e.getMessage());
		}
		Compte cptA_apres = compteService.rechercherCompteParNum(cptA.getNumero());
		Compte cptB_apres = compteService.rechercherCompteParNum(cptB.getNumero());
		System.out.println("avant mauvais virement : " + cptA.getSolde() + " " + cptB.getSolde());
		System.out.println("apres mauvais virement : " + cptA_apres.getSolde() + " " + cptB_apres.getSolde());
		Assertions.assertEquals(cptA_apres.getSolde(), cptA.getSolde()-0,0.0001);
		Assertions.assertEquals(cptB_apres.getSolde(), cptB.getSolde()+0,0.0001);
		
	}

}
