package com.esprit.examen.services;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
/*import com.esprit.examen.entities.Profession;*/

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FournisseurServiceImplTest {
	@Autowired
	IFournisseurService FournisseurService;

	
	@Test
	public void testAddFournisseur() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("30/09/2000");
		Fournisseur F = new Fournisseur("oumayma", "jrayed", dateNaissance, "oumayma.jrayed@esprit.tn", "code",
				CategorieFournisseur.ORDINAIRE);
		Fournisseur FOU = FournisseurService.addFournisseur(F);
		System.out.print("Fournisseur "+ FOU );
		assertNotNull(FOU.getIdFournisseur());
		assertNotNull(FOU.getCategorieFournisseur());
		assertTrue(FOU.getLibelle().length() > 0);
		FournisseurService.deleteFournisseur(FOU.getIdFournisseur());

	}
	@Test
	public void testDeleteFournisseur() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("30/09/2000");
		Fournisseur F = new Fournisseur("Salhi", "Ahmed", dateNaissance, "ahmed.salhi@esprit.tn", "CODE",
				CategorieFournisseur.CONVENTIONNE);
		Fournisseur FOUR = FournisseurService.addFournisseur(F);
		FournisseurService.deleteFournisseur(FOUR.getIdFournisseur());
		assertNull(FournisseurService. retrieveFournisseur(FOUR.getIdFournisseur()));
	}

	@Test
	public void testRetrieveAllFournisseurs() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNaissance = dateFormat.parse("30/09/2000");
		List<Fournisseur> Fournisseurs = FournisseurService.retrieveAllFournisseurs();
		int expected = Fournisseurs.size();
		Fournisseur f = new Fournisseur("Salhi", "Ahmed", dateNaissance, "ahmed.salhi@esprit.tn", "pwd",
				CategorieFournisseur.CONVENTIONNE);
		Fournisseur FOU = FournisseurService.addFournisseur(f);
		assertEquals(expected + 1, FournisseurService.retrieveAllFournisseurs().size());
		FournisseurService.deleteFournisseur(FOU.getIdFournisseur());

	}
	

}
