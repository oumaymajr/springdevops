package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CategorieProduitServiceImplTest {
	
	
	@Mock
	CategorieProduitRepository catProduitRepository;
	
	@InjectMocks
	CategorieProduitServiceImpl categorieProduitService;
    private WebApplicationContext webApplicationContext;

    //Creation de categorieProduit pour le test
    CategorieProduit categorieProduit = new CategorieProduit("cat001","alimentaire");
    List<CategorieProduit> listCategorieProduits = new ArrayList<CategorieProduit>(){
    {
    add(new CategorieProduit("cat002", "cosmétique"));
    add(new CategorieProduit("cat003", "alimentaire"));
    }
    };
	
	@Test
	@Order(1)
	public void testAddCategorieProduit() {
		
		log.debug("Tester Ajout de categorieProduit");
		when(catProduitRepository.save(ArgumentMatchers.any(CategorieProduit.class))).thenReturn(categorieProduit);
				
		// utiliser la methode dans le service		
		CategorieProduit created =categorieProduitService.addCategorieProduit(categorieProduit);
        Assertions.assertEquals(created.getLibelleCategorie(),(categorieProduit.getLibelleCategorie()));
	}	
	
	
	@Test
    @Order(2)
    void testRetrieveCategorieProduit() {

        log.debug("Tester retrive du CategorieProduit");
        when(catProduitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(categorieProduit));
        
        Long CP1 = null;
		// utiliser la methode dans le service
        CategorieProduit categorieProduitRetrived = categorieProduitService.retrieveCategorieProduit(CP1);
        // verifier que la CategorieProduit existe
        Assertions.assertNotNull(categorieProduitRetrived);

    }
	
	
	@Test
    @Order(3)
    void testUpdateCategorieProduit() {
        log.debug("Tester update du CategorieProduit");
        when(catProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
             
        // utiliser la methode dans le service
        CategorieProduit categorieProduitUpdated =categorieProduitService.updateCategorieProduit(categorieProduit);
        // verifier que la categorieProduit existe
        Assertions.assertNotNull(categorieProduitUpdated);
    }
	
	
	@Test
    @Order(4)
    void  testRetrieveAllCategorieProduits() throws Exception {
        log.debug("Tester Retrive All CategorieProduits");
        when(catProduitRepository.findAll()).thenReturn(listCategorieProduits);
        List<CategorieProduit> listCategorieProduits = categorieProduitService.retrieveAllCategorieProduits();
        Assertions.assertNotNull(listCategorieProduits);
	}
	
	@Test
    @Order(5)
    void testDeleteCategorieProduit() {
        log.debug("Test méthode Delete CategorieProduit");
        try {
           // when(catProduitRepository.findById(CategorieProduit.getIdCategorieProduit())).thenReturn(Optional.of(CategorieProduit));

            Long CP2 = null;
			categorieProduitService.deleteCategorieProduit(CP2);

            assertNull(categorieProduitService.retrieveCategorieProduit(CP2));
        } catch (Exception e) {
            log.error("méthode Delete CategorieProduit error :" + e);
        }

	}
}


