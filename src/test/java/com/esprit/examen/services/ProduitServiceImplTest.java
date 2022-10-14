package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest

@Slf4j
class ProduitServiceImplTest {

    @Autowired
    ProduitServiceImpl produitService;
    private WebApplicationContext webApplicationContext;

    Date datecreation = new Date("12/12/2002");
    Date dateexpiration = new Date("12/12/2022");

    // cree un produit pour tester
    Produit produit = new Produit(1L, "aa", "prod", 12.2F, datecreation, dateexpiration, null, null, null);


    @Test
    void retrieveAllProduits() throws Exception {
        log.debug("Tester Retrive All produits");
        List<Produit> produitList = produitService.retrieveAllProduits();
        Assertions.assertNotNull(produitList);

    }

    @Test
    void addProduit() {
        log.debug("Tester Ajout du produit");

        // utiliser la methode dans le service
        produitService.addProduit(produit);
        // verifier que produit existe
        Assertions.assertNotNull(produitService.retrieveProduit(1L));
    }

    @Test
    void deleteProduit() {
        log.debug("Test méthode Delete produit");
        try {
            produitService.deleteProduit(1L);

            assertNull(produitService.retrieveProduit(1L));
        } catch (Exception e) {
            log.error("méthode Delete produit error :" + e);
        }
    }


    @Test
    void updateProduit() {
        log.debug("Tester update du produit");

        // utiliser la methode dans le service
        produitService.updateProduit(produit);
        // verifier que produit existe
        Assertions.assertNotNull(produitService.retrieveProduit(1L));
    }

    @Test
    void retrieveProduit() {

        log.debug("Tester retrive du produit");

        // utiliser la methode dans le service
        Produit produitretrived = produitService.retrieveProduit(1L);
        // verifier que produit existe
        Assertions.assertNotNull(produitretrived);

    }
}