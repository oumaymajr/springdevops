package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;






import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Stock;
import static org.junit.Assert.*;

import com.esprit.examen.repositories.StockRepository;


@Service


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
	public void testUpdateLibelleByStockId() {
		l.debug("Test méthode Modifier Libelle StockbyId");
		try {
			String libelle= "stock1_test";

			stockService.UpdateLibelleStock_Id(libelle, (long) 42);

			Stock st = stockService.getStockById((long) 42);

			assertThat(st.getLibelleStock()).isEqualTo(libelle);
			l.info("Stock modified successfully!");
			
		} catch (Exception e) {
			l.error(String.format("ERROR : %s ", e));
		}
	}
	 
	@Test
	@Order(3)
	public void testretrieveStatusStock() {
		l.debug("Test méthode Status Stock");
		try {
			stockService.retrieveStatusStock();
			
		} catch (Exception e) {
			l.error("méthode Status Stock :"+ e);
		}
		
	}

	@Test
	@Order(4)
	public void testretrieveStock() {
		l.debug("Test méthode retrieve Stock");
		try {
			stockService.retrieveStock((long) 4);
			
		} catch (Exception e) {
			l.error("méthode retrieve Stock :"+ e);
		}
		
		
	}
	
	@Test
	@Order(5)
	public void testretrieveAllStocks() {
		l.debug("Test méthode Retrieve all Stocks");
		List<Stock> stocks = (List<Stock>) stockService.retrieveAllStocks();
		for (Stock stock : stocks) {
			l.info(stock.getLibelleStock() + ": stocks retrieved successfully!");
		}
		
	}
	@Test
	@Order(6)
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

	

}
