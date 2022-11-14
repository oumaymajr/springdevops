package com.esprit.examen.services;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategorieProduitServiceImplTest {

@Mock
CategorieProduitRepository or;
@InjectMocks
CategorieProduitServiceImpl os;

CategorieProduit o = new CategorieProduit("CategorieProduit1 ","CP20");

Long getId()
{
    for (CategorieProduit op: or.findAll()) {
        return op.getIdCategorieProduit();
    }
    return 0L;
}
@Test
@Order(0)
void TestaddCategorieProduit() {
	CategorieProduit op = new CategorieProduit();
    List<CategorieProduit> CategorieProduits = new ArrayList<>();
    for (Long i=1L;i<=10L;i++) {
        op.setIdCategorieProduit(i);;
        op.setLibelleCategorie("Lib1");
        op.setCodeCategorie("C12");


        CategorieProduit ca=or.save(op);
        CategorieProduits.add(ca);
    }
    assertEquals(10,CategorieProduits.size());
}
@Test
@Order(3)
void TestdeleteAllCategorieProduit() {
    or.deleteAll();
    assertEquals(0,or.findAll().spliterator().estimateSize());
}
@Test
@Order(2)
void TestretrieveCategorieProduit() {
    Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o));

    Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o))
    ;
    CategorieProduit op = os.retrieveCategorieProduit(2L);
    Assertions.assertNotNull(op);


}
@Test
@Order(4)
void TestgetAllCategorieProduits(){
    Iterable<CategorieProduit> CategorieProduits = or.findAll();
    Assertions.assertNotNull(CategorieProduits);
}

}	
