package com.esprit.examen.services;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StockServiceImplTest {

@Mock
StockRepository or;
@InjectMocks
StockServiceImpl os;

Stock o = new Stock("stock test",20,100);

Long getId()
{
    for (Stock op: or.findAll()) {
        return op.getIdStock();
    }
    return 0L;
}
@Test
@Order(0)
void TestaddStock() {
	Stock op = new Stock();
    List<Stock> Stocks = new ArrayList<>();
    for (Long i=1L;i<=10L;i++) {
        op.setIdStock(i);;
        op.setLibelleStock("test1");
        op.setQte(30);


        Stock ca=or.save(op);
        Stocks.add(ca);
    }
    assertEquals(10,Stocks.size());
}
@Test
@Order(3)
void TestdeleteAllStock() {
    or.deleteAll();
    assertEquals(0,or.findAll().spliterator().estimateSize());
}
@Test
@Order(2)
void TestretrieveStock() {
    Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o));

    Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(o))
    ;
    Stock op = os.retrieveStock(2L);
    Assertions.assertNotNull(op);


}
@Test
@Order(4)
void TestgetAllStocks(){
    Iterable<Stock> Stocks = or.findAll();
    Assertions.assertNotNull(Stocks);
}

}	