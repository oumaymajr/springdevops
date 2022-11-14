package com.esprit.examen.services;
import org.junit.jupiter.api.Test;

import java.util.List;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.CategorieProduit;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class CategorieProduitServiceImplJunitTest {
	@Autowired
	ICategorieProduitService categorieProduitService;

	@Test

	 void testRetrieveAllCategorieProduits()
{
		
		List<CategorieProduit> CategorieProduits = (List<CategorieProduit>) categorieProduitService.retrieveAllCategorieProduits();
		 Assertions.assertEquals(0, CategorieProduits.size());
		
	}
	
	@Test
	
	 void testAddCategorieProduit() {
		
		  List<CategorieProduit> categorieProduits = categorieProduitService.retrieveAllCategorieProduits();
		  int expected = categorieProduits.size();
		  CategorieProduit cp = new CategorieProduit("CategorieProduit_test","10");

		  CategorieProduit savedCategorieProduit= categorieProduitService.addCategorieProduit(cp);
		    assertEquals(expected+1, categorieProduitService.retrieveAllCategorieProduits().size());
		    assertNotNull(savedCategorieProduit.getLibelleCategorie());
		    categorieProduitService.deleteCategorieProduit(savedCategorieProduit.getIdCategorieProduit());
		
	}
	
	@Test

	 void testUpdateCategorieProduit() {
		    CategorieProduit cp = new CategorieProduit("CategorieProduit_test1","10");
		    CategorieProduit savedCategorieProduit= categorieProduitService.addCategorieProduit(cp);
		    savedCategorieProduit.setLibelleCategorie("test1");
		    categorieProduitService.updateCategorieProduit(savedCategorieProduit);
		    assertEquals(cp.getLibelleCategorie(),savedCategorieProduit.getLibelleCategorie());
		    categorieProduitService.deleteCategorieProduit(savedCategorieProduit.getIdCategorieProduit());

	}

	

	@Test

	 void testDeleteCategorieProduit() {
		
		 CategorieProduit cp = new CategorieProduit("CategorieProduit_test1","10");
		 CategorieProduit savedCategorieProduit= categorieProduitService.addCategorieProduit(cp);
		 categorieProduitService.deleteCategorieProduit(savedCategorieProduit.getIdCategorieProduit());
         assertNotNull(savedCategorieProduit.getIdCategorieProduit());
			
		
	}

	

}
