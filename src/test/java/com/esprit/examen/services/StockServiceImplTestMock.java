package com.esprit.examen.services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StockServiceImplTestMock {

@Mock
StockRepository or;
@InjectMocks
StockServiceImpl os;

Stock o = new Stock("stock_test",10,20);
Stock s = new Stock("stock_test",5,10);
Stock s1 = new Stock("stock_test1",2,10);

List<Stock> stockList = Arrays.asList(s,s1);

Long getId()
{
    for (Stock op: or.findAll()) {
        return op.getIdStock();
    }
    return 0L;
}
@Test
@Order(0)
void TestaddOperateur() {
	Stock op = new Stock();
    List<Stock> Stocks = new ArrayList<>();
    for (Long i=1L;i<=10L;i++) {
        op.setIdStock(i);
        op.setLibelleStock("stock1");
        op.setQte(20);

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
void TestgetAllOperateur(){
    Iterable<Stock> Stocks = or.findAll();
    Assertions.assertNotNull(Stocks);
}




}	