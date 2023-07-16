package com.dmart.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmart.model.StockItem;
import com.dmart.repository.StockItemRepository;
import com.dmart.service.StockItemService;
import com.dmart.service.exception.ResponseNotFoundException;
import com.dmart.utlDto.StockItemDto;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockItemServiceImpl implements StockItemService {
    private final StockItemRepository stockItemRepository;
    private  ModelMapper modelMapper;

    @Autowired
    public StockItemServiceImpl(StockItemRepository stockItemRepository, ModelMapper modelMapper) {
        this.stockItemRepository = stockItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StockItemDto addStockItem(StockItemDto stockItem) {
    	StockItem newStock = modelMapper.map(stockItem, StockItem.class);
         StockItem saveStock = stockItemRepository.save(newStock);
         return modelMapper.map(saveStock, StockItemDto.class);
    }

    @Override
    public StockItemDto updateStockItem(StockItemDto stockItem, Long stockItemId) {
    	StockItem newStock = modelMapper.map(stockItem, StockItem.class);
     	StockItemDto stockItemById = getStockItemById(stockItemId); //check  stock is percent in dub or not
     	newStock.setId(stockItemId);
     	newStock.setQuantity(newStock.getQuantity()+stockItemById.getQuantity());
        StockItem saveStock = stockItemRepository.save(newStock);
        return modelMapper.map(saveStock, StockItemDto.class);
    }

    @Override
    public String deleteStockItem(Long stockItemId) {
    	getStockItemById(stockItemId); //check  stock is percent in db or not
        stockItemRepository.deleteById(stockItemId);
        return "Stock is deleted successfully...";
    }

    @Override
    public StockItemDto getStockItemById(Long stockItemId) {
         StockItem findStock = stockItemRepository.findById(stockItemId)
                .orElseThrow(() -> new ResponseNotFoundException("Stock", "stock Id", stockItemId));
         return modelMapper.map(findStock, StockItemDto.class);
    }

    @Override
    public List<StockItemDto> getAllStockItems() {
    	List<StockItemDto> allUserDetos = stockItemRepository.findAll().stream().map(stock -> modelMapper.map(stock, StockItemDto.class)).collect(Collectors.toList());
    	return allUserDetos;
    }
}

