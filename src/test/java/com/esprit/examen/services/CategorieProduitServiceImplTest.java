package com.esprit.examen.services;

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
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

@Service
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class CategorieProduitServiceImplTest {
	
	
	@Mock
	CategorieProduitRepository catProduitRepository;
	
	@InjectMocks
	CategorieProduitServiceImpl categorieProduitService;

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

		when(catProduitRepository.save(ArgumentMatchers.any(CategorieProduit.class))).thenReturn(categorieProduit);
				
		// utiliser la methode dans le service		
		CategorieProduit created =categorieProduitService.addCategorieProduit(categorieProduit);
        Assertions.assertEquals(created.getLibelleCategorie(),(categorieProduit.getLibelleCategorie()));
	}	
	
	
	@Test
    @Order(2)
    void testRetrieveCategorieProduit() {

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
        when(catProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);
             
        // utiliser la methode dans le service
        CategorieProduit categorieProduitUpdated =categorieProduitService.updateCategorieProduit(categorieProduit);
        // verifier que la categorieProduit existe
        Assertions.assertNotNull(categorieProduitUpdated);
    }
	
	
	@Test
    @Order(4)
    void  testRetrieveAllCategorieProduits() throws Exception {
        when(catProduitRepository.findAll()).thenReturn(listCategorieProduits);
        List<CategorieProduit> listCategorieProduits = categorieProduitService.retrieveAllCategorieProduits();
        Assertions.assertNotNull(listCategorieProduits);
	}
	
	@Test
    @Order(5)
    void testDeleteCategorieProduit() {
        
           // when(catProduitRepository.findById(CategorieProduit.getIdCategorieProduit())).thenReturn(Optional.of(CategorieProduit));

            Long CP2 = null;
			categorieProduitService.deleteCategorieProduit(CP2);

            assertNull(categorieProduitService.retrieveCategorieProduit(CP2));
       
	}
}


