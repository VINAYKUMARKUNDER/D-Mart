package com.dmart.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.model.StockItem;
import com.dmart.model.StockMovement;
import com.dmart.model.StoreLocation;
import com.dmart.repository.StockMovementRepository;
import com.dmart.service.StockItemService;
import com.dmart.service.StockMovementService;
import com.dmart.service.StoreLocationService;
import com.dmart.utlDto.StockItemDto;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class StockMovementServiceImpl implements StockMovementService {
	private final StockMovementRepository stockMovementRepository;
	private final StockItemService stockItemService;
	private final StoreLocationService storeLocationService;
	private final ModelMapper modelMapper;

	@Autowired
	public StockMovementServiceImpl(StockMovementRepository stockMovementRepository, StockItemService stockItemService,
			StoreLocationService storeLocationService, ModelMapper modelMapper) {

		this.stockMovementRepository = stockMovementRepository;
		this.stockItemService = stockItemService;
		this.storeLocationService = storeLocationService;
		this.modelMapper = modelMapper;
	}

	@Override
	public <T> T addStockMovement(StockMovement stockMovement) {

		StockItem stockItem = stockMovement.getStockItem();
		StoreLocation fromLocation = stockMovement.getFromLocation();
		StoreLocation toLocation = stockMovement.getToLocation();
		int quantity = stockMovement.getQuantity();

		StockItemDto perentStockItem = this.stockItemService.getStockItemById(stockItem.getId());
		StoreLocation fromLocationData = this.storeLocationService.findLocationById(fromLocation.getId());
		StoreLocation toLocationData = this.storeLocationService.findLocationById(toLocation.getId());
		
		
		if(fromLocation.getId() == toLocation.getId() ) {
			return (T) String.format("First Location is %d and Second Location is %d Both are Equal. same location is not allow...", fromLocation.getId(), toLocation.getId()); 
		}
		
		 List<StockItem> fromLocationStockItem = fromLocationData.getStockItems();
		 List<StockItem> toLocationStockItem = toLocationData.getStockItems();
		 
		 boolean flag = false;
		 for(int i =0;i<fromLocationStockItem.size();i++) {
			 StockItem stock = fromLocationStockItem.get(i);
			 if(stock.getId() == stockItem.getId()) {
				 stock.setQuantity(stock.getQuantity()-quantity);
				 fromLocationStockItem.add(i,stock);
				 flag=true;
				 break;
			 }
		 }
		 
		 if(!flag)   return (T) String.format("%s Stock is not found in %s  %s."
		 		+ " plz add  %s stock in %s %s storage. ", perentStockItem.getName(),
		 		fromLocationData.getName(), fromLocationData.getAddress(),
		 		perentStockItem.getName(),fromLocationData.getName(), fromLocationData.getAddress());
		
		 System.out.println("line 72.................................");
		 
		 for(int i =0;i<toLocationStockItem.size();i++) {
			 StockItem stock = fromLocationStockItem.get(i);
			 if(stock.getId() == stockItem.getId()) {
				 stock.setQuantity(stock.getQuantity()+quantity);
				 toLocationStockItem.add(i,stock);
				 flag=true;
				 break;
			 }
		 }
		 if(!flag) {
			 StockItem stocs= stockItem;
			 stocs.setQuantity(quantity); 
			 toLocationStockItem.add(stocs);
		 }
		 
		 System.out.println("line 89.................................");
		 fromLocationData.setStockItems(fromLocationStockItem);
		 toLocationData.setStockItems(toLocationStockItem);
		 
		 storeLocationService.updateQuantity(fromLocationData, fromLocationData.getId());
		 
		 System.out.println("line 94.................................");
		 
		 storeLocationService.updateQuantity(toLocationData, toLocationData.getId());
	
		 System.out.println("line 97.................................");
		 
		stockMovement.setMovementDate(LocalDateTime.now());
		 stockMovementRepository.save(stockMovement);
		 
		 System.out.println("line 100.................................");
		 
		 return (T) "stock Movements successfully";
	}
	
	

	@Override
	public List<StockMovement> getAllStockMovements() {
		return stockMovementRepository.findAll();
	}
}
