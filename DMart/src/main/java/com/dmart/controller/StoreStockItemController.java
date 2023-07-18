package com.dmart.controller;

import com.dmart.model.StoreStockItem;
import com.dmart.service.StoreStockItemService;
import com.dmart.utlDto.ResponceModel;
import com.dmart.utlDto.StoreStockItemDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/store-stock-items")
public class StoreStockItemController {

    private final StoreStockItemService storeStockItemService;

    @Autowired
    public StoreStockItemController(StoreStockItemService storeStockItemService) {
        this.storeStockItemService = storeStockItemService;
    }

    @PostMapping
    public ResponseEntity<ResponceModel> createStoreStockItem(@RequestBody StoreStockItemDto storeStockItem) {
        StoreStockItemDto createdItem = storeStockItemService.createStoreStockItem(storeStockItem);
        ResponceModel msg = new ResponceModel(createdItem, HttpStatus.CREATED,201, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponceModel> getStoreStockItemById(@PathVariable Long id) {
        StoreStockItem storeStockItem = storeStockItemService.getStoreStockItemById(id);
        ResponceModel msg = new ResponceModel(storeStockItem, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @GetMapping
    public ResponseEntity<ResponceModel> getAllStoreStockItems() {
        List<StoreStockItemDto> storeStockItems = storeStockItemService.getAllStoreStockItems();
        ResponceModel msg = new ResponceModel(storeStockItems, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponceModel> updateStoreStockItem(@RequestBody StoreStockItemDto storeStockItem, @PathVariable Long id, @PathVariable Long locationId) {
        StoreStockItemDto updatedItem = storeStockItemService.updateStoreStockItem(storeStockItem, id,locationId);
        ResponceModel msg = new ResponceModel(updatedItem, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponceModel> deleteStoreStockItem(@PathVariable Long id) {
        storeStockItemService.deleteStoreStockItem(id);
        ResponceModel msg = new ResponceModel("Deleted successfully..", HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
    
    @GetMapping("/store-location/{storeLocationId}")
    public ResponseEntity<ResponceModel> getStoreStockItemsByStoreLocationId(@PathVariable Long storeLocationId) {
        List<StoreStockItemDto> storeStockItems = storeStockItemService.getStoreStockItemsByStoreLocationId(storeLocationId);
        ResponceModel msg = new ResponceModel(storeStockItems, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
    
    
    @PostMapping("/{id}/stock/{stockId}/addQuantity/{quantity}/")
    public ResponseEntity<ResponceModel> addQuantityInStore(@PathVariable Long id, @PathVariable Long stockId, @PathVariable Integer quantity) {
        String result = storeStockItemService.addQuantityInStore(quantity, id, stockId);
        ResponceModel msg = new ResponceModel(result, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
