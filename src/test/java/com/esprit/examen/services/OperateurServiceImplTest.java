package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

public class OperateurServiceImplTest {
private static final Logger l = LogManager.getLogger(OperateurServiceImplTest.class);

@Mock
private OperateurRepository or;
@InjectMocks
private OperateurServiceImpl os;

@Autowired 
IOperateurService operateurservice	;

Operateur o1 = new Operateur("bkh", "Issa","***");
Operateur o2 = new Operateur("abc", "Oum","***");
List<Operateur> operateurlist = Arrays.asList(o1,o2);
@Test
@Order(1)
public void TestAddOperateur () {
	when(or.save(o1)).thenReturn(o1);
    assertNotNull(o1);
    assertEquals(o1,os.addOperateur(o1));
	System.out.print("operateur "+ o1.getPrenom()+ " added succesfully");
	}

@Test
@Order(5)

public void TestDeleteOperateur() {
	l.debug("Test méthode DeleteOperateur");
	try {
		operateurservice.deleteOperateurById((long) 6); 
		
		assertNull(os.getOperateurById((long) 6));
		l.info(" operateur deleted succesfully");
	} catch (Exception e) {
		l.error("méthode Delete Operateur error :"+ e);
	}
	
}
@Test
@Order(2)
public void TestUpdateNomById() {
	l.debug("Test méthode Modifier Nom d'un operateur by id");
	try {
		String nom= "devops";

		operateurservice.UpdateNomBy_Id(nom, (long) 3);

		Operateur o = operateurservice.getOperateurById((long) 3);

		assertThat(o.getNom()).isEqualTo(nom);
		l.info("nom operateur modified successfully!");
		
	} catch (Exception e) {
		l.error(String.format("ERROR : %s ", e));
	}
}

@Test
@Order(3)
public void TestUpdateOperateur() {
    when(or.save(o1)).thenReturn(o1);
    assertNotNull(o1);
    assertEquals(o1, os.updateOperateur(o1));

    System.out.println("Operateur Updated Successfully !");
}
@Test
@Order(4)
public void TestRetrieveAllOperateurs() {
		l.debug("Test méthode Retrieve Operateurs");
        when(or.findAll()).thenReturn(operateurlist);
		List<Operateur> operateurlist = (List<Operateur>) operateurservice.retrieveAllOperateurs();
		Assertions.assertNotNull(operateurlist);
        l.info("Retrieve  All Operateurs done !!!");
		}
	}



	


	
	
	
	

	
	
	



