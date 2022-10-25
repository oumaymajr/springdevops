package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j

@RunWith(SpringRunner.class)
@SpringBootTest

public class FournisseurServiceImpTest {
	
	@Autowired
	IFournisseurService fournisseurService;
	@Autowired
	FournisseurRepository fournisseurRepository;
	
	private static final org.apache.logging.log4j.Logger l = LogManager.getLogger(FournisseurServiceImpTest.class);
	
	
	@Test
	@Order(1)
	public void TestAddFournisseur() {
		l.debug("Test méthode Ajouter Fournisseur");
		Fournisseur f = new Fournisseur("Test","Test",CategorieFournisseur.ORDINAIRE);
		Fournisseur savedFournisseur= fournisseurService.addFournisseur(f);
		//System.out.print("Stock "+ savedStock );
		l.info("Fournisseur added successfully!");
		assertNotNull(savedFournisseur.getLibelle());
		
	} 
	 

	@Test
	@Order(3)
	public void testDeleteFournisseur() {
		l.debug("Test méthode Delete deleteFournisseur");
		try {
			fournisseurService.deleteFournisseur((long) 2);
			
			assertNull(fournisseurService.getFournisseurById((long) 2));
			l.info("Fournisseur deleted successfully!");
		} catch (Exception e) {
			l.error("méthode Delete error :"+ e);
		}
		
	}
	
	@Test
	@Order(2)
	public void testUpdateLibelleByFournisseurId() {
		l.debug("Test méthode Modifier Libelle FournisseurId");
		try {
			String libelle= "test2";

			fournisseurService.UpdateLibelleFournisseurId(libelle, (long) 1);

			Fournisseur st = fournisseurService.getFournisseurById((long) 1);

			assertThat(st.getLibelle()).isEqualTo(libelle);
			l.info("Fournisseur modified successfully!");
			
		} catch (Exception e) {
			l.error(String.format("ERROR : %s ", e));
		}
	}
	
	
	/*@Test
	@Order(4)
	public List<Fournisseur> retrieveAllFournisseurs() {
		l.debug("Test méthode Retrieve Fournisseur");
		List<Fournisseur> fournisseurs = (List<Fournisseur>) fournisseurService.retrieveAllFournisseurs();
		for (Fournisseur fournisseur : fournisseurs) {
			l.info(fournisseur + "Fournisseur retrieved successfully!");
		}
		return fournisseurs;
	}*/
	
	
	

}