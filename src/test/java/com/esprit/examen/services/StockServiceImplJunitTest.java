package com.esprit.examen.services;
import org.junit.jupiter.api.Test;

import java.util.List;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Stock;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class StockServiceImplJunitTest {
	@Autowired
	IStockService stockService;

	@Test

	 void testRetrieveAllStocks()
{
		
		List<Stock> stocks = (List<Stock>) stockService.retrieveAllStocks();
		 Assertions.assertEquals(0, stocks.size());
		
	}
	
	@Test
	
	 void testAddStock() {
		
		  List<Stock> stocks = stockService.retrieveAllStocks();
		  int expected = stocks.size();
		  Stock s = new Stock("stock_test",10,20);

		    Stock savedStock= stockService.addStock(s);
		    assertEquals(expected+1, stockService.retrieveAllStocks().size());
		    assertNotNull(savedStock.getLibelleStock());
		    stockService.deleteStock(savedStock.getIdStock());
		
	}
	
	@Test

	 void testUpdateStock() {
		    Stock s = new Stock("stock_test1",10,50);
		    Stock savedStock= stockService.addStock(s);
		    savedStock.setLibelleStock("test1");
		    stockService.updateStock(savedStock);
		    assertEquals(s.getLibelleStock(),savedStock.getLibelleStock());
		    stockService.deleteStock(savedStock.getIdStock());

	}

	

	@Test

	 void testDeleteStock() {
		
		 Stock s = new Stock("stock_test1",10,50);
		 Stock savedStock= stockService.addStock(s);
		 stockService.deleteStock(savedStock.getIdStock());
         assertNotNull(savedStock.getIdStock());
			
		
	}

	

}
