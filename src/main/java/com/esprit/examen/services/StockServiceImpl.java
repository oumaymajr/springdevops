package com.esprit.examen.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServiceImpl implements IStockService {

	@Autowired
	StockRepository stockRepository;


	@Override
	public List<Stock> retrieveAllStocks() {
		// récuperer la date à l'instant t1
		log.info("In method retrieveAllStocks");
		List<Stock> stocks = stockRepository.findAll();
		for (Stock stock : stocks) {
			log.info(" Stock : " + stock);
		}
		log.info("out of method retrieveAllStocks");
		// récuperer la date à l'instant t2
		// temps execution = t2 - t1
		return stocks;
	}

	@Override
	public Stock addStock(Stock s) {
		// récuperer la date à l'instant t1
		log.info("In method addStock");
		return stockRepository.save(s);
		
	}

	@Override
	public void deleteStock(Long stockId) {
		log.info("In method deleteStock");
		stockRepository.deleteById(stockId);

	}

	@Override
	public Stock updateStock(Stock s) {
		log.info("In method updateStock");
		return stockRepository.save(s);
	}

	@Override
	public Stock retrieveStock(Long stockId) {
		long start = System.currentTimeMillis();
		log.info("In method retrieveStock");
		Stock stock = stockRepository.findById(stockId).orElse(null);
		log.info("out of method retrieveStock");
		 long elapsedTime = System.currentTimeMillis() - start;
		log.info("Method execution time: " + elapsedTime + " milliseconds.");

		return stock;
	}

	@Override
	public String retrieveStatusStock() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String msgDate = sdf.format(now);
		String finalMessage = "";
		String newLine = System.getProperty("line.separator");
		List<Stock> stocksEnRouge = stockRepository.retrieveStatusStock();
		for (int i = 0; i < stocksEnRouge.size(); i++) {
			finalMessage = newLine + finalMessage + msgDate + newLine + ": le stock "
					+ stocksEnRouge.get(i).getLibelleStock() + " a une quantité de " + stocksEnRouge.get(i).getQte()
					+ " inférieur à la quantité minimale a ne pas dépasser de " + stocksEnRouge.get(i).getQteMin()
					+ newLine;

		}
		log.info(finalMessage);
		return finalMessage;
	}

	
//////////////////////////////////// deleteStockById/////////////////////////////////////////////////////

	@Override
		public void deleteStockById(Long stcokid) {
			log.debug("methode deletestockById ");
			try {
				Optional<Stock> st = stockRepository.findById(stcokid);
				if(st.isPresent()){
				Stock s = st.get();
				stockRepository.delete(s);
				log.debug("deleteStcokById fini avec succes ");
				}
				else {
					log.error("erreur methode deleteStcokById : " );
				}
			} catch (Exception e) {
				log.error("erreur methode deleteStcokById : " +e);
				
			}

		}

	@Override
	public Stock getStockById(Long stockid) {
			log.debug("methode getStcokById ");
			try {
				Stock st= stockRepository.findById(stockid).orElse(null);
				log.debug("getStockId fini avec succes ");
				return st;
			} catch (Exception e) {
				log.error("erreur methode getStcokById : " +e);
				return null;
			}
		}

	@Override
	public void UpdateLibelleStock_Id(String libelle, Long ids) {
		Stock st = stockRepository.findById(ids).orElse(null);
		if(st!=null){
		st.setLibelleStock(libelle);
		stockRepository.save(st);
		
		}
		
	}
	

		
	
	

}