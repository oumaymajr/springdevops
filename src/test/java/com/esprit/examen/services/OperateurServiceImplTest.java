package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Operateur;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)

public class OperateurServiceImplTest {
	private static final Logger l = LogManager.getLogger(OperateurServiceImplTest.class);


@Autowired 
IOperateurService operateurservice	;
@Test
@Order(1)
public void TestAddOperateur () {
	Operateur o = new Operateur("ben foulen", "foulen", "abc123");
	Operateur osaved= operateurservice.addOperateur(o);
	System.out.print("operateur "+osaved);
	assertNotNull(o.getIdOperateur());
}

@Test
@Order(2)

public void TestDeleteOperateur() {
	l.debug("Test méthode DeleteOperateur");
	try {
		operateurservice.deleteOperateurById((long)5); 
		
		assertNull(operateurservice.getOperateurById((long)5));
	} catch (Exception e) {
		l.error("méthode DeleteEntreprise error :"+ e);
	}
	
}
@Test
@Order(3)
public void TestUpdateNomById() {
	l.debug("Test méthode Modifier Nom d'un operateur by id");
	try {
		String nom= "devops";

		operateurservice.UpdateNomById(nom, (long) 4);

		Operateur o = operateurservice.getOperateurById((long) 4);

		assertThat(o.getNom()).isEqualTo(nom);
		l.info("nom operateur modified successfully!");
		
	} catch (Exception e) {
		l.error(String.format("ERROR : %s ", e));
	}
}

}
	


	
	
	
	

	
	
	



