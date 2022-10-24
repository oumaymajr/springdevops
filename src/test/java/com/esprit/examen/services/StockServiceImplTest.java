package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Stock;

import com.esprit.examen.repositories.StockRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {
	
	@Autowired
	IStockService stockService;
	@Autowired
	StockRepository stockRepository;
	
	private static final org.apache.logging.log4j.Logger l = LogManager.getLogger(StockServiceImplTest.class);
	
	
	@Test
	@Order(1)
	public void TestAddStock() {
		l.debug("Test méthode Ajouter Stock");
		Stock s = new Stock("stock_test",10,20);
		Stock savedStock= stockService.addStock(s);
		//System.out.print("Stock "+ savedStock );
		l.info("Stock added successfully!");
		assertNotNull(savedStock.getLibelleStock());
		
		
	} 
	 

	@Test
	@Order(2)
	public void testDeleteStockById() {
		l.debug("Test méthode Delete StockById");
		try {
			stockService.deleteStockById((long) 39);
			
			assertNull(stockService.getStockById((long) 39));
			l.info("Stock deleted successfully!");
		} catch (Exception e) {
			l.error("méthode Delete error :"+ e);
		}
		
	}
	@Test
	@Order(3)
	public void testUpdateLibelleByStockId() {
		l.debug("Test méthode Modifier Libelle StockbyId");
		try {
			String libelle= "stock1_test";

			stockService.UpdateLibelleStockId(libelle, (long) 42);

			Stock st = stockService.getStockById((long) 42);

			assertThat(st.getLibelleStock()).isEqualTo(libelle);
			l.info("Stock modified successfully!");
			
		} catch (Exception e) {
			l.error(String.format("ERROR : %s ", e));
		}
	}
	
	@Test
	@Order(4)
	public void testretrieveStatusStock() {
		l.debug("Test méthode Status Stock");
		try {
			stockService.retrieveStatusStock();
			
		} catch (Exception e) {
			l.error("méthode Status Stock :"+ e);
		}
		
	}
	

	@Test
	//@Order(5)
	public void retrieveStock() {
		l.debug("Test méthode retrieve Stock");
		try {
			stockService.retrieveStock((long) 4);
			
		} catch (Exception e) {
			l.error("méthode retrieve Stock :"+ e);
		}
		
	}
	
	

}

	


