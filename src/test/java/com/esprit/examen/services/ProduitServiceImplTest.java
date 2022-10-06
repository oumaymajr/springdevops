package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class ProduitServiceImplTest {


    @Autowired
    ProduitServiceImpl produitService;
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void retrieveAllProduits() throws Exception {

       /* mockMvc.perform(get("/retrieve-produit/{produit-id}")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("emp1")).andExpect(jsonPath("$.designation").value("manager"))
                .andExpect(jsonPath("$.produit-id").value("1")).andExpect(jsonPath("$.salary").value(3000));

                private Long idProduit;
	private String codeProduit;
	private String libelleProduit;
	private float prix;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	@Temporal(TemporalType.DATE)
	private Date dateDerniereModification;
                */

    }

    @Test
    void addProduit() {


        //Produit produit = new Produit(12,12,"prodtest",222.0,"12/12/2012","12/12/2022");
        Produit produit = new Produit();
        Produit addprod= produitService.addProduit(produit);
        assertNotNull(produitService);

    }

    @Test
    void deleteProduit() {
    }

    @Test
    void updateProduit() {
    }

    @Test
    void retrieveProduit() {
    }

    @Test
    void assignProduitToStock() {
    }
}