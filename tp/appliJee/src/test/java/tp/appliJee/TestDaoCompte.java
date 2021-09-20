package tp.appliJee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliJee.dao.CompteDAO;
import tp.appliJee.entity.Compte;

//Test Dao basé sur JUnit 4 ou 5/jupiter
//@RunWith(SpringRunner.class) si junit5
@ExtendWith(SpringExtension.class) //pour junit5/jupiter
@SpringBootTest(classes= {AppliJeeApplication.class})  //NB: AppliJeeApplication avec main , config
public class TestDaoCompte {
	
	@Autowired //injection de dépendance spring (proche @Inject de CDI)
	private CompteDAO compteDAO; //à tester
	
	@Test()
	public void test1() {
		Compte cptA = compteDAO.save(new Compte(null,"Compte A", 38.67));
		System.out.println("cptA sauvegardé avec numero/pk = " + cptA.getNumero());
		
		Compte cptB = compteDAO.save(new Compte(null,"Compte B", 34.7));
		
		//relecture en base pour vérifier la bonne insertion:
		Compte cptARelu = compteDAO.findById(cptA.getNumero()).get();  //optional.get() soulève exception si empty
	    //Compte cptARelu = compteDAO.findById(cptA.getNumero()).orElse(null); //retourne null si empty
		
		System.out.println("cptARelu="+cptARelu);
		
		//Assert.assertTrue(...); //si en junit 4
		Assertions.assertTrue(cptARelu.getLabel().equals(cptA.getLabel())); //si en junit5/jupiter
	}
	

}
