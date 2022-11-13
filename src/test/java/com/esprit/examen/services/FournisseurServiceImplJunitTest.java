package com.esprit.examen.services;
import org.junit.jupiter.api.Test;

import java.util.List;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


class FournisseurServiceImplJunitTest {
	@Autowired
	IFournisseurService fournisseurService;

	@Test

	 void testRetrieveAllFournisseurs()
{
		
		List<Fournisseur> fournisseurs = (List<Fournisseur>) fournisseurService.retrieveAllFournisseurs();
		 Assertions.assertEquals(0, fournisseurs.size());
		
	}
	
	@Test
	
	 void testAddFournisseur() {
		
		  List<Fournisseur> fournisseurs = fournisseurService.retrieveAllFournisseurs();
		  int expected = fournisseurs.size();
		  Fournisseur f = new Fournisseur("fournisseur_test","test",CategorieFournisseur.ORDINAIRE);

		  Fournisseur savedFournisseur= fournisseurService.addFournisseur(f);
		    assertEquals(expected+1, fournisseurService.retrieveAllFournisseurs().size());
		    assertNotNull(savedFournisseur.getLibelle());
		    fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
		
	}
	
	@Test

	 void testUpdateFournisseur() {
			Fournisseur f = new Fournisseur("fournisseur_test","test",CategorieFournisseur.ORDINAIRE);
			Fournisseur savedFournisseur= fournisseurService.addFournisseur(f);
			savedFournisseur.setLibelle("test1");
			fournisseurService.updateFournisseur(savedFournisseur);
		    assertEquals(f.getLibelle(),savedFournisseur.getLibelle());
		    fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());

	}

	

	@Test

	 void testDeleteFournisseur() {
		
			Fournisseur f = new Fournisseur("fournisseur_test","test",CategorieFournisseur.ORDINAIRE);
			Fournisseur savedFournisseur= fournisseurService.addFournisseur(f);
			fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
			assertNotNull(savedFournisseur.getIdFournisseur());
			
		
	}

	

}