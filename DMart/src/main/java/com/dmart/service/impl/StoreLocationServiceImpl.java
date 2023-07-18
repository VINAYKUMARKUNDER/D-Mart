package com.dmart.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.model.StockItem;
import com.dmart.model.StoreLocation;
import com.dmart.model.StoreStockItem;
import com.dmart.repository.StoreLocationRepository;
import com.dmart.service.StockItemService;
import com.dmart.service.StoreLocationService;
import com.dmart.service.StoreStockItemService;
import com.dmart.service.exception.ResponseNotFoundException;
import com.dmart.utlDto.StockItemDto;
import com.dmart.utlDto.StoreLocationDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreLocationServiceImpl implements StoreLocationService {
    private final StoreLocationRepository storeLocationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StoreLocationServiceImpl(StoreLocationRepository storeLocationRepository,
    		 ModelMapper modelMapper) {
        this.storeLocationRepository = storeLocationRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public StoreLocationDto addStoreLocation(StoreLocationDto storeLocation) {
    	StoreLocation mappedData = modelMapper.map(storeLocation, StoreLocation.class);
        StoreLocation savedData = storeLocationRepository.save(mappedData);
        return modelMapper.map(savedData, StoreLocationDto.class);
    }

    @Override
    public List<StoreLocationDto> getAllStoreLocations() {
    	
    	List<StoreLocationDto> collect = storeLocationRepository.findAll().stream().map(store -> modelMapper.map(store, StoreLocationDto.class)).collect(Collectors.toList());
    	return collect;
    }

	@Override
	public StoreLocation findLocationById(Long Id) {
		 StoreLocation Location = storeLocationRepository.findById(Id)
                .orElseThrow(() -> new ResponseNotFoundException("Store Location", "Location Id", Id));
        
		return Location;
	}

	

	
}
