package tp.jeeApp.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import tp.jeeApp.JeeAppApplication;
import tp.jeeApp.entity.Client;
import tp.jeeApp.entity.Compte;

/*
  Assert.assertTrue(...) //en junit 4
  Assertions.assertTrue(...); //en junit 5
 */

@ExtendWith(SpringExtension.class) //pour junit 5 , @RunWith(SpringRunner.class) si junit 4
@SpringBootTest(classes= {JeeAppApplication.class})
public class TestCompteDao {
	
	@Autowired
	private CompteDao compteDao; //à tester
	
	@Autowired
	private ClientDao clientDao; //pour aider à tester
	
	/*
	//@Test
	public void testBasicV1a(){
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
		cptA.setProprietaire(savedClient1); //sens indispensable (principal,sauvegardé)
		savedClient1.addCompte(cptA); //sens secondaire , pour cohérence (pas sauvegardé)
		Compte savedCptA = compteDao.save(cptA);
		System.out.println("savedCptA="+savedCptA.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedCptA !=null); //en junit 5
		
		
		Compte cptB  = new Compte(null,"compteB",50.0);
		cptB.setProprietaire(savedClient1); //sens indispensable (principal,sauvegardé)
		savedClient1.addCompte(cptB);//sens secondaire , pour cohérence (pas sauvegardé)
		Compte savedCptB = compteDao.save(cptB);
		System.out.println("savedCptB="+savedCptB.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedCptB !=null); //en junit 5
		
		Compte cptC  = new Compte(null,"compteC",50.0);
		cptC.setProprietaire(savedClient2); //sens indispensable (principal,sauvegardé)
		//savedClient2.addCompte(cptC);//sens secondaire , pour cohérence (pas sauvegardé)
		Compte savedCptC = compteDao.save(cptC);
		System.out.println("savedCptC="+savedCptC.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(savedCptC !=null); //en junit 5
		
		//List<Compte> comptes = compteDao.findAll();
		List<Compte> comptes = compteDao.findComptesByClient(savedClient1.getNumero());
		Assertions.assertTrue(comptes.size() >=2); 
		System.out.println("comptes du client1:");
		comptes.stream().forEach( (c)->{ System.out.println("\t" + c.toString()); });
	}
	*/
	
	@Test
	public void testBasicV1b(){
		Client client1 = clientDao.save(new Client(null,"alex","Therieur"));
		System.out.println("savedClient1="+client1.toString());
		Assertions.assertTrue(client1 !=null); //en junit 5
		
		Client client2 = clientDao.save(new Client(null,"jean","Bon"));
		System.out.println("savedClient2="+client2.toString());
		Assertions.assertTrue(client2 !=null); //en junit 5
		
		Compte cptA  = new Compte(null,"compteA",100.0);
		cptA.setProprietaire(client1); //sens indispensable (principal,sauvegardé)
		client1.addCompte(cptA); //sens secondaire , pour cohérence (pas sauvegardé)
		cptA = compteDao.save(cptA);
		System.out.println("savedCptA="+cptA.toString());
		Assertions.assertTrue(cptA !=null); //en junit 5
		
		
		Compte cptB  = new Compte(null,"compteB",50.0);
		cptB.setProprietaire(client1); //sens indispensable (principal,sauvegardé)
		client1.addCompte(cptB);//sens secondaire , pour cohérence (pas sauvegardé)
		cptB = compteDao.save(cptB);
		System.out.println("savedCptB="+cptB.toString());
		//Assert.assertTrue(...) //en junit 4
		Assertions.assertTrue(cptB !=null); //en junit 5
		
		Compte cptC  = new Compte(null,"compteC",50.0);
		cptC.setProprietaire(client2); //sens indispensable (principal,sauvegardé)
		client2.addCompte(cptC);//sens secondaire , pour cohérence (pas sauvegardé)
		cptC = compteDao.save(cptC);
		System.out.println("savedCptC="+cptC.toString());
		Assertions.assertTrue(cptC !=null); //en junit 5
		
		//List<Compte> comptes = compteDao.findAll();
		List<Compte> comptes = compteDao.findComptesByClient(client1.getNumero());
		Assertions.assertTrue(comptes.size() >=2); 
		System.out.println("comptes du client1:");
		comptes.stream().forEach( (c)->{ System.out.println("\t" + c.toString()); });
		
		try {
			client1.setNumero(-1L);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		List<Client> clientsJeanBons = clientDao.findByPrenomAndNom("jean","Bon");
		Assertions.assertTrue(clientsJeanBons.size() ==1);
		System.out.println("clientsJeanBons=" + clientsJeanBons.get(0));
		
	}
	
	/*
	@Test
	@Transactional
	public void testBasicV2AvecTransactional(){
		
		Compte cptA  = compteDao.save(new Compte(null,"compteA",100.0));
		System.out.println("savedCptA="+cptA.toString());
		Assertions.assertTrue(cptA !=null); //en junit 5
		
		
		Compte cptB  = compteDao.save(new Compte(null,"compteB",50.0));
		System.out.println("savedCptB="+cptB.toString());
		Assertions.assertTrue(cptB !=null); 
		
		Compte cptC  = compteDao.save(new Compte(null,"compteC",50.0));
		System.out.println("savedCptC="+cptC.toString());
		Assertions.assertTrue(cptC !=null); 
		
		Client client1 = new Client(null,"alex","Therieur");
		
		client1.addCompte(cptA); //sens secondaire : coté mappedBy, pour cohérence (pas sauvegardé par défaut)
		cptA.setProprietaire(client1); //eventuel effet indirect de .addCompte , sens principal,sauvegardé en base par défaut
		
	
		client1.addCompte(cptB);//sens secondaire : coté mappedBy, pour cohérence (pas sauvegardé par défaut)
		cptB.setProprietaire(client1); //eventuel effet indirect de .addCompte , sens principal,sauvegardé en base par défaut
		
		client1 = clientDao.save(client1);
		//effet indirect du contexte @Transactional --> commit / flush / persistance automatique des comptes pas détachés
		
		System.out.println("savedClient1="+client1.toString());
		Assertions.assertTrue(client1 !=null); //en junit 5
		
		Client client2 = new Client(null,"jean","Bon");
		
		client2.addCompte(cptC);//sens secondaire : coté mappedBy, pour cohérence (pas sauvegardé)
		cptC.setProprietaire(client2); //eventuel effet indirect de .addCompte , sens principal,sauvegardé en base par défaut
		
		client2 = clientDao.save(client2);
		
		System.out.println("savedClient2="+client2.toString());
		Assertions.assertTrue(client2 !=null); //en junit 5
		
		
		//List<Compte> comptes = compteDao.findAll();
		List<Compte> comptes = compteDao.findComptesByClient(client1.getNumero());
		System.out.println("comptes du client1:");
		comptes.stream().forEach( (c)->{ System.out.println("\t" + c.toString()); });
		Assertions.assertTrue(comptes.size() >=2); 
	
	}
	*/
	/*
	//@Test
	public void testBasicV2bAvecCascadeEtDoubleSaveClient(){
		
		Compte cptA  = compteDao.save(new Compte(null,"compteA",100.0));
		System.out.println("savedCptA="+cptA.toString());
		Assertions.assertTrue(cptA !=null); //en junit 5
		
		
		Compte cptB  = compteDao.save(new Compte(null,"compteB",50.0));
		System.out.println("savedCptB="+cptB.toString());
		Assertions.assertTrue(cptB !=null); 
		
		Compte cptC  = compteDao.save(new Compte(null,"compteC",50.0));
		System.out.println("savedCptC="+cptC.toString());
		Assertions.assertTrue(cptC !=null); 
		
		Client client1 = clientDao.save(new Client(null,"alex","Therieur"));
		
		client1.addCompte(cptA); //sens secondaire : coté mappedBy, pour cohérence (pas sauvegardé par défaut)
		cptA.setProprietaire(client1); //eventuel effet indirect de .addCompte , sens principal,sauvegardé en base par défaut

		client1.addCompte(cptB);//sens secondaire : coté mappedBy, pour cohérence (pas sauvegardé par défaut)
		cptB.setProprietaire(client1); //eventuel effet indirect de .addCompte , sens principal,sauvegardé en base par défaut
		
		//NB : les relations uniquement effectuées du coté secondaire
		//peuvent tout de même être sauvegardées en base si CASCADE sur client déjà un peu persisté (via .save() antérieur)
		client1 = clientDao.save(client1);
		
		System.out.println("savedClient1="+client1.toString());
		Assertions.assertTrue(client1 !=null); //en junit 5
		
		Client client2 = clientDao.save(new Client(null,"jean","Bon"));
		
		client2.addCompte(cptC);//sens secondaire : coté mappedBy, pour cohérence (pas sauvegardé)
		cptC.setProprietaire(client2); //eventuel effet indirect de .addCompte , sens principal,sauvegardé en base par défaut
		
		//NB : les relations uniquement effectuées du coté secondaire
		//peuvent tout de même être sauvegardées en base si CASCADE sur client déjà un peu persisté (via .save() antérieur)
		client2 = clientDao.save(client2);
		
		System.out.println("savedClient2="+client2.toString());
		Assertions.assertTrue(client2 !=null); //en junit 5
		
		
		//List<Compte> comptes = compteDao.findAll();
		List<Compte> comptes = compteDao.findComptesByClient(client1.getNumero());
		System.out.println("comptes du client1:");
		comptes.stream().forEach( (c)->{ System.out.println("\t" + c.toString()); });
		Assertions.assertTrue(comptes.size() >=2); 
	
	}
    */
}
