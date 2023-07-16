package com.dmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmart.model.StockItem;
import com.dmart.service.StockItemService;
import com.dmart.utlDto.ResponceModel;
import com.dmart.utlDto.StockItemDto;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/stock-items")
public class StockItemController {
    private final StockItemService stockItemService;

    @Autowired
    public StockItemController(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponceModel> addStockItem(@RequestBody @Valid StockItemDto stockItem) {
    	StockItemDto addedStockItem = stockItemService.addStockItem(stockItem);
		ResponceModel msg = new ResponceModel(addedStockItem, HttpStatus.CREATED,201, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @PutMapping("/{stockItemId}")
    public ResponseEntity<ResponceModel> updateStockItem(
            @PathVariable Long stockItemId, @RequestBody StockItemDto stockItem) {
        StockItemDto updatedStockItem = stockItemService.updateStockItem(stockItem,stockItemId);
        ResponceModel msg = new ResponceModel(updatedStockItem, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @DeleteMapping("/{stockItemId}")
    public ResponseEntity<ResponceModel> deleteStockItem(@PathVariable Long stockItemId) {
        String internalMsg = stockItemService.deleteStockItem(stockItemId);
        ResponceModel msg = new ResponceModel(internalMsg, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @GetMapping("/{stockItemId}")
    public ResponseEntity<ResponceModel> getStockItemById(@PathVariable Long stockItemId) {
        StockItemDto stockItem = stockItemService.getStockItemById(stockItemId);
        ResponceModel msg = new ResponceModel(stockItem, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @GetMapping("/")
    public ResponseEntity<ResponceModel> getAllStockItems() {
        List<StockItemDto> stockItems = stockItemService.getAllStockItems();
        ResponceModel msg = new ResponceModel(stockItems, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
    
   
}
