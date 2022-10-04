package com.esprit.examen.services;

import static org.junit.Assert.*;

import java.text.ParseException;

/*import java.util.List;*/
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Stock;

import com.esprit.examen.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {
	
	@Autowired
	IStockService stockService;
	
	@Test
	public void testAddStock() {
		//List<Stock> stocks = stockService.retrieveAllStocks();
		//int expected=stocks.size();
		log.debug("ADD method");
		Stock s = new Stock("stock4",20,10);
		Stock savedStock= stockService.addStock(s);
		
	    //assertEquals(expected+1, stockService.retrieveAllStocks().size());
		//assertNotNull(savedStock.getLibelleStock());
		//stockService.deleteStock(savedStock.getIdStock());
		
	} 
	@Test
	public void testDeleteStock() {
		log.debug("Test méthode DeleteStock");
		try {
			stockService.deleteStockById((long) 8); 
			
			assertNull(stockService.getStockById((long) 8));
		} catch (Exception e) {
			log.error("méthode DeleteStock error :"+ e);
		}	}
	
	

}

	


