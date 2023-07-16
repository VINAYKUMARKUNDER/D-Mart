package com.dmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.model.StockItem;
import com.dmart.model.StoreLocation;
import com.dmart.repository.StoreLocationRepository;
import com.dmart.service.StockItemService;
import com.dmart.service.StoreLocationService;
import com.dmart.service.exception.ResponseNotFoundException;
import com.dmart.utlDto.StockItemDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreLocationServiceImpl implements StoreLocationService {
    private final StoreLocationRepository storeLocationRepository;
    private final StockItemService stockItemService;

    @Autowired
    public StoreLocationServiceImpl(StoreLocationRepository storeLocationRepository, StockItemService stockItemService) {
        this.storeLocationRepository = storeLocationRepository;
        this.stockItemService=stockItemService;
    }

    @Override
    public StoreLocation addStoreLocation(StoreLocation storeLocation) {
        return storeLocationRepository.save(storeLocation);
    }

    @Override
    public List<StoreLocation> getAllStoreLocations() {
        return storeLocationRepository.findAll();
    }

	@Override
	public StoreLocation findLocationById(Long Id) {
		 StoreLocation Location = storeLocationRepository.findById(Id)
                .orElseThrow(() -> new ResponseNotFoundException("Store Location", "Location Id", Id));
        
		return Location;
	}

	@Override
	public void updateQuantity(StoreLocation location, Long Id) {
		this.findLocationById(Id);
		System.out.println("in Update Quantity methodd..");
		storeLocationRepository.save(location);
		
	}

	@Override
	public String addQuantityInStore(Integer quantity, Long id, Long stockId) {
		StoreLocation findLocationById = this.findLocationById(id);
		StockItemDto stockItemById = stockItemService.getStockItemById(stockId);

		List<StockItem> stockItem = findLocationById.getStockItems();
		
		
		int stocks =0;
		for(StockItem stock:stockItem) {
			System.out.println(stock.getId()+" "+ stock.getQuantity());
			if(stock.getId()==stockId)stocks+=stock.getQuantity();
		}
		
		if((stocks+quantity) > stockItemById.getQuantity() ) {
			return "Your Perent stock quantity is minimum first add more stock in parent stock..."+stockItemById.getQuantity() +" "+(stocks+quantity);
		}
		
		boolean flag = false;
		for(int i=0;i<stockItem.size();i++) {
			if(stockItem.get(i).getId()==stockId) {
			stockItem.get(i).setQuantity(stockItem.get(i).getQuantity()+quantity);
				stockItem.add(i,stockItem.get(i));
				flag=true;
			}
		}
		
		if(!flag) {
			StockItem st = new StockItem(stockId,stockItemById.getName(),quantity);
			stockItem.add(st);
		}
		
		findLocationById.setStockItems(stockItem);
		this.updateQuantity(findLocationById, id);
		
		return "Add quantity successfully.";
	}
}
