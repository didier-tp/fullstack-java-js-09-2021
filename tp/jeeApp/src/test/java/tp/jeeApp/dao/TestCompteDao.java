package tp.jeeApp.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.jeeApp.JeeAppApplication;
import tp.jeeApp.entity.Client;
import tp.jeeApp.entity.Compte;

@ExtendWith(SpringExtension.class) //pour junit 5 , @RunWith(SpringRunner.class) si junit 4
@SpringBootTest(classes= {JeeAppApplication.class})
public class TestCompteDao {
	
	@Autowired
	private CompteDao compteDao; //à tester
	
	@Autowired
	private ClientDao clientDao; //pour aider à tester
	
	
	@Test
	public void testBasic(){
		Client client1 = new Client(null,"alex","Therieur");
		Client savedClient1 = clientDao.save(client1);
		System.out.println("savedClient1="+savedClient1.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedClient1 !=null); //en junit 5
		
		Client client2 = new Client(null,"jean","Bon");
		Client savedClient2 = clientDao.save(client2);
		System.out.println("savedClient2="+savedClient2.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedClient2 !=null); //en junit 5
		
		Compte cptA  = new Compte(null,"compteA",100.0);
		cptA.setProprietaire(savedClient1);
		Compte savedCptA = compteDao.save(cptA);
		System.out.println("savedCptA="+savedCptA.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedCptA !=null); //en junit 5
		
		
		Compte cptB  = new Compte(null,"compteB",50.0);
		cptB.setProprietaire(savedClient1);
		Compte savedCptB = compteDao.save(cptB);
		System.out.println("savedCptB="+savedCptB.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedCptB !=null); //en junit 5
		
		Compte cptC  = new Compte(null,"compteC",50.0);
		cptC.setProprietaire(savedClient2);
		Compte savedCptC = compteDao.save(cptC);
		System.out.println("savedCptB="+savedCptC.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedCptC !=null); //en junit 5
		
		
		//client1.addCompte(cptA); client1.addCompte(cptB);
		
		
		//client2.addCompte(cptC);
		
		
		//List<Compte> comptes = compteDao.findAll();
		List<Compte> comptes = compteDao.findComptesByClient(client1.getNumero());
		Assertions.assertTrue(comptes.size() >=2); 
		comptes.stream().forEach( (c)->{ System.out.println(c.toString()); });
		
	}

}
