package tp.jeeApp.dao;

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
public class TestCompteDao {
	
	@Autowired
	private CompteDao compteDao; //à tester
	
	
	@Test
	public void testBasic(){
		Compte cptA  = new Compte(null,"compteA",100.0);
		Compte savedCptA = compteDao.save(cptA);
		System.out.println("savedCptA="+savedCptA.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedCptA !=null); //en junit 5
	}

}
