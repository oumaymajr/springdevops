package com.esprit.examen.services;
import org.junit.jupiter.api.Test;

import java.util.List;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.SecteurActivite;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class SecteurActiviteImplTest {
	
	@Autowired
	ISecteurActiviteService ss;
	 
	 @Test
	    void retrieveAllSecteurActivites() {
	    	List<SecteurActivite> SecteurActivites = ss.retrieveAllSecteurActivite();
	        Assertions.assertEquals(0, SecteurActivites.size());
	    }
	 @Test
	     void testAddSecteurActivite(){
		 
		    List<SecteurActivite> SecteurActivites = ss.retrieveAllSecteurActivite();
		    int expected = SecteurActivites.size();
		    SecteurActivite s = new SecteurActivite("ndq","Assurance");

		    SecteurActivite savedSecteurActivite= ss.addSecteurActivite(s);
		    assertEquals(expected+1, ss.retrieveAllSecteurActivite().size());
		    assertNotNull(savedSecteurActivite.getLibelleSecteurActivite());
		    ss.deleteSecteurActivite(savedSecteurActivite.getIdSecteurActivite());

	    }
	 
	 @Test
	     void testUpdateSecteurActivite() {
		    SecteurActivite s = new SecteurActivite("*****","Assurance");
		    SecteurActivite savedSecteurActivite= ss.addSecteurActivite(s);
		    savedSecteurActivite.setLibelleSecteurActivite("64654654");
		    ss.updateSecteurActivite(savedSecteurActivite);
		    assertEquals(s.getLibelleSecteurActivite(),savedSecteurActivite.getLibelleSecteurActivite());
		    ss.deleteSecteurActivite(savedSecteurActivite.getIdSecteurActivite());
	    }

	 @Test
	     void testDeleteSecteurActivite() {
		    SecteurActivite s = new SecteurActivite("123136","Tajhiz");
	            SecteurActivite savedSecteurActivite= ss.addSecteurActivite(s);
		    ss.deleteSecteurActivite(savedSecteurActivite.getIdSecteurActivite());
		    assertNotNull(savedSecteurActivite.getIdSecteurActivite());

	    }

	
}