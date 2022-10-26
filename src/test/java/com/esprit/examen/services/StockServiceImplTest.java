package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;



import java.util.Arrays;
import java.util.List;




import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
	 
	// cree un stock pour tester 
	 Stock s = new Stock("stock_test",5,10);
	 List<Stock> stockList = Arrays.asList(s);
	 
	 @Test
	    @Order(1)
	    public void testAddStock() {
	        log.debug("Tester Ajout du stock");
	        when(stockrepository.save(ArgumentMatchers.any(Stock.class))).thenReturn(s);
	        // utiliser la methode dans le service
	        Stock created =stockserivce.addStock(s);
	        Assertions.assertEquals(created.getLibelleStock(),(s.getLibelleStock()));
	    }
	 @Test
	    @Order(2)
	    public void  testRetrieveAllStocks() throws Exception {
	        log.debug("Tester Retrive All stocks");
	        when(stockrepository.findAll()).thenReturn(stockList);
	        List<Stock> stockList = stockserivce.retrieveAllStocks();
	        Assertions.assertNotNull(stockList);
	 }

	        
		@Test
		@Order(3)
		public void testUpdateLibelleByStockId() {
			log.debug("Test méthode Modifier Libelle StockbyId");
			try {
				String libelle= "stock1_test";

				stockserivce.UpdateLibelleStockId(libelle, (long) 42);

				Stock st = stockserivce.getStockById((long) 42);

				assertThat(st.getLibelleStock()).isEqualTo(libelle);
				log.info("Stock modified successfully!");
				
			} catch (Exception e) {
				log.error(String.format("ERROR : %s ", e));
			}
		}
		@Test
		@Order(4)
		public void testretrieveStatusStock() {
			log.debug("Test méthode Status Stock");
			try {
				stockserivce.retrieveStatusStock();
				
			} catch (Exception e) {
				log.error("méthode Status Stock :"+ e);
			}
			
		}
		@Test
		@Order(5)
		public void retrieveStock() {
			log.debug("Test méthode retrieve Stock");
			try {
				stockserivce.retrieveStock((long) 4);
				
			} catch (Exception e) {
				log.error("méthode retrieve Stock :"+ e);
			}
			
			
		}
	 
		 @Test
		@Order(6)
		public void testDeleteStockById() {
		 log.debug("Test méthode Delete StockById");
			try {
				stockserivce.deleteStockById((long) 39);
				
				assertNull(stockserivce.getStockById((long) 39));
				log.info("Stock deleted successfully!");
			} catch (Exception e) {
				log.error("méthode Delete error :"+ e);
			}
			
		}

}

	


