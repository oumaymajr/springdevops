package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = ProduitServiceImplTest.class)

@Slf4j
class ProduitServiceImplTest {

    @Mock
    ProduitRepository produitRepository;
    @InjectMocks
    ProduitServiceImpl produitService;
    private WebApplicationContext webApplicationContext;

    Date datecreation = new Date("12/12/2002");
    Date dateexpiration = new Date("12/12/2022");

    // cree un produit pour tester
    Produit produit = new Produit(1L, "aa",
                        "prod", 12.2F, datecreation, dateexpiration);
    List<Produit> produitList = Arrays.asList(produit);


    @Test
    @Order(3)
    void  testRetrieveAllProduits() throws Exception {
        log.debug("Tester Retrive All produits");
        when(produitRepository.findAll()).thenReturn(produitList);
        List<Produit> produitList = produitService.retrieveAllProduits();
        Assertions.assertNotNull(produitList);

    }

    @Test
    @Order(1)
    void testAddProduit() {
        log.debug("Tester Ajout du produit");
        when(produitRepository.save(ArgumentMatchers.any(Produit.class))).thenReturn(produit);

        // utiliser la methode dans le service
        Produit created =produitService.addProduit(produit);
        // verifier que produit existe
        //Assertions.assertNotNull(produitService.retrieveProduit(1L));
        Assertions.assertEquals(created.getLibelleProduit(),(produit.getLibelleProduit()));
    }

    @Test
    @Order(5)
    void testDeleteProduit() {
        log.debug("Test méthode Delete produit");
        try {
           // when(produitRepository.findById(produit.getIdProduit())).thenReturn(Optional.of(produit));

            produitService.deleteProduit(1L);

            assertNull(produitService.retrieveProduit(1L));
        } catch (Exception e) {
            log.error("méthode Delete produit error :" + e);
        }
    }


    @Test
    @Order(3)
    void testUpdateProduit() {
        log.debug("Tester update du produit");
        when(produitRepository.save(produit)).thenReturn(produit);

        // utiliser la methode dans le service
        Produit produitUpdated =produitService.updateProduit(produit);
        // verifier que produit existe
        Assertions.assertNotNull(produitUpdated);
    }

    @Test
    @Order(2)
    void testRetrieveProduit() {


        log.debug("Tester retrive du produit");
        when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));
        // utiliser la methode dans le service
        Produit produitretrived = produitService.retrieveProduit(1L);
        // verifier que produit existe
        Assertions.assertNotNull(produitretrived);

    }
}
