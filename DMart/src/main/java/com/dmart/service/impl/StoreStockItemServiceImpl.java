package com.dmart.service.impl;

import com.dmart.model.StockItem;
import com.dmart.model.StoreLocation;
import com.dmart.model.StoreStockItem;
import com.dmart.repository.StoreStockItemRepository;
import com.dmart.service.StockItemService;
import com.dmart.service.StoreLocationService;
import com.dmart.service.StoreStockItemService;
import com.dmart.service.exception.ResponseNotFoundException;
import com.dmart.utlDto.StockItemDto;
import com.dmart.utlDto.StoreLocationDto;
import com.dmart.utlDto.StoreStockItemDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreStockItemServiceImpl implements StoreStockItemService {

    private final StoreStockItemRepository storeStockItemRepository;
    private final StoreLocationService storeLocationService;
    private final StockItemService stockItemService;
    private final ModelMapper modelMapper;


    @Autowired
    public StoreStockItemServiceImpl(StoreStockItemRepository storeStockItemRepository,
    		StoreLocationService storeLocationService,
    		StockItemService stockItemService, ModelMapper modelMapper) {
        this.storeStockItemRepository = storeStockItemRepository;
        this.storeLocationService = storeLocationService;
        this.stockItemService =stockItemService;
        this.modelMapper = modelMapper;
    }

    @Override
    public StoreStockItemDto createStoreStockItem(StoreStockItemDto storeStockItem) {
    	StoreStockItem mapData = modelMapper.map(storeStockItem, StoreStockItem.class);
    
         StoreStockItem saveData = storeStockItemRepository.save(mapData);
         return modelMapper.map(saveData, StoreStockItemDto.class);
    }

    @Override
    public StoreStockItem getStoreStockItemById(Long id) {
    	 StoreStockItem item = storeStockItemRepository.findById(id)
                 .orElseThrow(() -> new ResponseNotFoundException("Store Stock", "stock Id", id));
        
        return item;
    }

    @Override
    public List<StoreStockItemDto> getAllStoreStockItems() {
    	List<StoreStockItemDto> collect = storeStockItemRepository.findAll().stream().map(storeStock -> modelMapper.map(storeStock, StoreStockItemDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public StoreStockItemDto updateStoreStockItem(StoreStockItemDto storeStockItem, Long id, Long storeLocationId) {
    	this.getStoreStockItemById(id);
    	StoreLocation findLocationById = storeLocationService.findLocationById(storeLocationId);
    	StoreStockItem maped = new StoreStockItem(id,storeStockItem.getStockName(),storeStockItem.getStockQuantity(),findLocationById);
         StoreStockItem saveData = storeStockItemRepository.save(maped);
         return modelMapper.map(saveData, StoreStockItemDto.class);
    }

    @Override
    public void deleteStoreStockItem(Long id) {
    	this.getStoreStockItemById(id);
        storeStockItemRepository.deleteById(id);
    }
    
    @Override
    public List<StoreStockItemDto> getStoreStockItemsByStoreLocationId(Long storeLocationId) {

    	
         List<StoreStockItem> storeStockItemsByStoreLocationId = storeStockItemRepository.getStoreStockItemsByStoreLocationId(storeLocationId);
         System.out.println(storeStockItemsByStoreLocationId);
         List<StoreStockItemDto> collect = storeStockItemsByStoreLocationId.stream().map(storeStock -> modelMapper.map(storeStock, StoreStockItemDto.class)).collect(Collectors.toList());
         return collect;
         
    }
    
    
    List<StoreStockItem> getAllStoreStockWithLocation(Long storeLocationId){
    	return storeStockItemRepository.getStoreStockItemsByStoreLocationId(storeLocationId);
    }
    
    
    @Override
	public String addQuantityInStore(Integer quantity, Long id, Long stockId) {
    	System.out.println(quantity+" "+ id+" " +stockId);
		StoreLocation findLocationById = storeLocationService.findLocationById(id);
		StockItemDto stockItemById = stockItemService.getStockItemById(stockId);

		List<StoreStockItem> storeStockItemsByStoreLocationId = this.getAllStoreStockWithLocation(id);
//		List<StockItem> stockItem = findLocationById.getStockItems();
		
		
		int stocks =0;
		for(StoreStockItem stock:storeStockItemsByStoreLocationId) {
			
//			System.out.println(stock.getId()+" "+ stock.getStockQuantity());
			if(stock.getId()==stockId)stocks+=stock.getStockQuantity();
		}
		
		System.out.println("stocks "+ stocks+" "+stockItemById.getQuantity());
		if((stocks+quantity) > stockItemById.getQuantity() ) {
			return "Your Perent stock quantity is minimum first add more stock in parent stock...";
		}
		
//		System.out.println(storeStockItemsByStoreLocationId);
		boolean flag = false;
		for(int i=0;i<storeStockItemsByStoreLocationId.size();i++) {
			System.out.println(storeStockItemsByStoreLocationId.get(i).getId()+" "+stockId);
			if(storeStockItemsByStoreLocationId.get(i).getId()==stockId) {
				storeStockItemsByStoreLocationId.get(i).setStockQuantity(storeStockItemsByStoreLocationId.get(i).getStockQuantity()+quantity);
				storeStockItemsByStoreLocationId.add(i,storeStockItemsByStoreLocationId.get(i));
				
				flag=true;
				this.updateStoreStockItem(modelMapper.map(storeStockItemsByStoreLocationId.get(i), StoreStockItemDto.class), storeStockItemsByStoreLocationId.get(i).getId(), storeStockItemsByStoreLocationId.get(i).getStoreLocation().getId());
				return "update quantity successfully.";
			}
		}
		
		if(!flag) {
			StoreLocation mapLocation = modelMapper.map(findLocationById, StoreLocation.class);
			StoreStockItem st = new StoreStockItem(null,stockItemById.getName(),quantity,mapLocation);
			storeStockItemRepository.save(st);
		}
		
//		findLocationById.setStockItems(stockItem);
//		this.updateQuantity(findLocationById, id);
		
		return "Add quantity successfully.";
	}
    
    
    @Override
	public void updateQuantity(StoreLocationDto location, Long Id) {
//		StoreLocation findLocationById = storeLocationService.findLocationById(Id);
//		System.out.println("in Update Quantity methodd..");
//		findLocationById.get
//		StoreLocation storeLocation = modelMapper.map(location, StoreLocation.class);
//		storeLocation.setId(Id);
////		storeLocationRepository.save(storeLocation);
		
	}

}
