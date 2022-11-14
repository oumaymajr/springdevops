package com.esprit.examen.services;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OperateurServiceImplTest {

@Mock
OperateurRepository or;
@InjectMocks
OperateurServiceImpl os;

Operateur o = new Operateur("afandich","issa","****");

Long getId()
{
    for (Operateur op: or.findAll()) {
        return op.getIdOperateur();
    }
    return 0L;
}
@Test
@Order(0)
void TestaddOperateur() {
	Operateur op = new Operateur();
    List<Operateur> Operateurs = new ArrayList<>();
    for (Long i=1L;i<=10L;i++) {
        op.setIdOperateur(i);
        op.setNom("oumayma");
        op.setPrenom("afandvic");
        op.setPassword("aaaaa");

        Operateur ca=or.save(op);
        Operateurs.add(ca);
    }
    assertEquals(10,Operateurs.size());
}
@Test
@Order(3)
void TestdeleteAllOperateur() {
    or.deleteAll();
    assertEquals(0,or.findAll().spliterator().estimateSize());
}
@Test
@Order(2)
void TestretrieveOperateur() {
    Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o));

    Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o))
    ;
    Operateur op = os.retrieveOperateur(2L);
    Assertions.assertNotNull(op);


}
@Test
@Order(4)
void TestgetAllOperateur(){
    Iterable<Operateur> Operateurs = or.findAll();
    Assertions.assertNotNull(Operateurs);
}

}	
