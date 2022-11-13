package com.esprit.examen.services;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FournisseurServiceImplTest {

@Mock
FournisseurRepository fr;
@InjectMocks
FournisseurServiceImpl of;

Fournisseur f = new Fournisseur("fournisseur test","test",CategorieFournisseur.CONVENTIONNE);

Long getId()
{
    for (Fournisseur f: fr.findAll()) {
        return f.getIdFournisseur();
    }
    return 0L;
}
@Test
@Order(0)
void TestaddFournissuer() {
	Fournisseur f = new Fournisseur();
    List<Fournisseur> fournisseurs = new ArrayList<>();
    for (Long i=1L;i<=10L;i++) {
        f.setIdFournisseur(i);;
        f.setLibelle(null);
        f.setCode("Test");


        Fournisseur ff=fr.save(f);
        fournisseurs.add(ff);
    }
    assertEquals(10,fournisseurs.size());
}
@Test
@Order(3)
void TestdeleteAllFournisseurs() {
    fr.deleteAll();
    assertEquals(0,fr.findAll().spliterator().estimateSize());
}
@Test
@Order(2)
void TestretrieveFournisseur() {
    Mockito.when(fr.findById(Mockito.anyLong())).thenReturn(Optional.of(f));

    Mockito.when(fr.findById(Mockito.anyLong())).thenReturn(Optional.of(f))
    ;
    Fournisseur f = of.retrieveFournisseur(2L);
    Assertions.assertNotNull(f);


}
@Test
@Order(4)
void TestgetAllFournisseurs(){
    Iterable<Fournisseur> fournisseurs = fr.findAll();
    Assertions.assertNotNull(fournisseurs);
}

}	