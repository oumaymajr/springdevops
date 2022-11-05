package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;



import java.util.Arrays;
import java.util.List;




import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Stock;
import static org.junit.Assert.*;

import com.esprit.examen.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {
	
	 @Mock
	 StockRepository stockrepository;
	 @InjectMocks
	 StockServiceImpl stockserivce;
	 @Autowired 
	 IStockService st;
	 
	 
	// cree des stocks pour tester 
	 
	 Stock s = new Stock("stock_test",5,10);
	 Stock s1 = new Stock("stock_test1",2,10);
	 
	 List<Stock> stockList = Arrays.asList(s,s1);
	 
	 @Test
	    @Order(1)
	    public void TestAddStock() {
	        log.info("Test add stock");
	        when(stockrepository.save(s)).thenReturn(s);
	        Stock created =stockserivce.addStock(s);
	        Assertions.assertEquals(created.getLibelleStock(),(s.getLibelleStock()));
	        log.info("Stock"+s.getLibelleStock()+"added succesfully");
	    }
	 @Test
	    @Order(2)
	    public void  TestRetrieveAllStocks() {
	        log.info("Test Retrive All stocks");
	        when(stockrepository.findAll()).thenReturn(stockList);
	        List<Stock> stockList = stockserivce.retrieveAllStocks();
	        Assertions.assertNotNull(stockList);
	        log.info("Retrieve All Stocks Works !");
	 }

	 	@Test
	 	@Order(3)
	 	public void TestUpdateStock() {
	 	log.info("Test Update stock");
        when(stockrepository.save(s)).thenReturn(s);
        assertNotNull(s);
        assertEquals(s, stockserivce.updateStock(s));
        log.info("Stock updated succesfully !");
    }

	        
		@Test
		@Order(4)
		public void TestUpdateLibelleByStockId() {
			log.info("Test Update Libelle StockbyId");
			try {
				String libelle= "stock1_test";

				stockserivce.UpdateLibelleStock_Id(libelle, (long) 42);

				Stock st = stockserivce.getStockById((long) 42);

				assertThat(st.getLibelleStock()).isEqualTo(libelle);
				log.info("Stock modified successfully!");
				
			} catch (Exception e) {
				log.error(String.format("ERROR : %s ", e));
			}
		}
		@Test
		@Order(5)
		public void TestRetrieveStatusStock() {
			log.info("Test Status Stock");
			try {
				stockserivce.retrieveStatusStock();
				Assertions.assertNotNull(stockList);
				
			} catch (Exception e) {
				log.error(" Status Stock ERROR :"+ e);
			}
			
		}
		@Test
		@Order(6)
		public void RetrieveStock() {
			log.info("Test retrieve Stock");
			try {
				stockserivce.retrieveStock((long) 4);
				assertNull(stockserivce.getStockById((long) 4));
				
			} catch (Exception e) {
				log.error("retrieve Stock ERROR :"+ e);
			}
			
			
		}
	 
		 @Test
		@Order(7)
		public void TestDeleteStockById() {
		 log.debug("Test méthode Delete StockById");
			try {
				st.deleteStockById((long) 47);
				
				assertNull(st.getStockById((long) 47));
				log.info("Stock deleted successfully!");
			} catch (Exception e) {
				log.error("Delete ERROR :"+ e);
			}
			
		}

}

	


