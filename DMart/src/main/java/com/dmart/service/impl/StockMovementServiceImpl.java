package com.dmart.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.model.StockItem;
import com.dmart.model.StockMovement;
import com.dmart.model.StoreLocation;
import com.dmart.model.StoreStockItem;
import com.dmart.repository.StockMovementRepository;
import com.dmart.repository.StoreStockItemRepository;
import com.dmart.service.StockItemService;
import com.dmart.service.StockMovementService;
import com.dmart.service.StoreLocationService;
import com.dmart.service.StoreStockItemService;
import com.dmart.utlDto.StockItemDto;
import com.dmart.utlDto.StoreLocationDto;
import com.dmart.utlDto.StoreStockItemDto;
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
	private final StoreStockItemRepository storeStockItemRepository;
	private final StoreStockItemService storeStockItemService;

	@Autowired
	public StockMovementServiceImpl(StockMovementRepository stockMovementRepository, StockItemService stockItemService,
			StoreLocationService storeLocationService, ModelMapper modelMapper, StoreStockItemRepository storeStockItemRepository,
			StoreStockItemService storeStockItemService) {

		this.stockMovementRepository = stockMovementRepository;
		this.stockItemService = stockItemService;
		this.storeLocationService = storeLocationService;
		this.modelMapper = modelMapper;
		this.storeStockItemRepository=storeStockItemRepository;
		this.storeStockItemService = storeStockItemService;
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
		
		
		List<StoreStockItem> fromLocationStocks = storeStockItemRepository.getStoreStockItemsByStoreLocationId(fromLocation.getId());
		List<StoreStockItem> toLocationStocks = storeStockItemRepository.getStoreStockItemsByStoreLocationId(toLocation.getId());
		
//		 List<StockItem> fromLocationStockItem = fromLocationData.getStockItems();
//		 List<StockItem> toLocationStockItem = toLocationData.getStockItems();
		 
		 boolean flag = false;
		 for(int i =0;i<fromLocationStocks.size();i++) {
			 StoreStockItem stock = fromLocationStocks.get(i);
			 if(stock.getId() == stockItem.getId()) {
				 stock.setStockQuantity(stock.getStockQuantity()-quantity);
				 storeStockItemService.updateStoreStockItem(modelMapper.map(stock, StoreStockItemDto.class), stock.getId() , fromLocation.getId());
				 flag=true;
				 break;
			 }
		 }
		 
		 if(!flag)   return (T) String.format("%s Stock is not found in %s  %s."
		 		+ " plz add  %s stock in %s %s storage. ", perentStockItem.getName(),
		 		fromLocationData.getName(), fromLocationData.getAddress(),
		 		perentStockItem.getName(),fromLocationData.getName(), fromLocationData.getAddress());
		
//		 System.out.println("line 72.................................");
		 
		 for(int i =0;i<toLocationStocks.size();i++) {
			 StoreStockItem stock = toLocationStocks.get(i);
			 if(stock.getId() == stockItem.getId()) {
				 stock.setStockQuantity(stock.getStockQuantity()+quantity);
				 storeStockItemRepository.updateStoreStockItemByStoreLocationId(stock.getId(), stock.getStockName(), stock.getStockQuantity(), stock.getId());
				 storeStockItemService.updateStoreStockItem(modelMapper.map(stock, StoreStockItemDto.class), stock.getId() , toLocation.getId());
				 flag=true;
				 break;
			 }
		 }
		 if(!flag) {
//			 StoreLocation mapLocation = modelMapper.map(findLocationById, StoreLocation.class);
				StoreStockItem st = new StoreStockItem(null,stockItem.getName(),quantity,toLocation);
				storeStockItemService.createStoreStockItem(modelMapper.map(st, StoreStockItemDto.class));
		 }
		 
//		 System.out.println("line 89.................................");
		
		 
		 
//		 storeLocationService.updateQuantity(fromLocationData, fromLocationData.getId());
//		 
//		 System.out.println("line 94.................................");
//		 
//		 storeLocationService.updateQuantity(toLocationData, toLocationData.getId());
	
//		 System.out.println("line 97.................................");
		 
		 
		 
		stockMovement.setMovementDate(LocalDateTime.now());
		 stockMovementRepository.save(stockMovement);
		 
//		 System.out.println("line 100.................................");
		 
		 return (T) "stock Movements successfully";
	}
	
	

	@Override
	public List<StockMovement> getAllStockMovements() {
		return stockMovementRepository.findAll();
	}
}
