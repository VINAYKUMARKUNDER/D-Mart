package com.dmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmart.model.StoreLocation;
import com.dmart.service.StoreLocationService;
import com.dmart.utlDto.ResponceModel;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/store-locations")
public class StoreLocationController {
    private final StoreLocationService storeLocationService;

    @Autowired
    public StoreLocationController(StoreLocationService storeLocationService) {
        this.storeLocationService = storeLocationService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponceModel> addStoreLocation(@RequestBody StoreLocation storeLocation) {
        StoreLocation addedStoreLocation = storeLocationService.addStoreLocation(storeLocation);
        ResponceModel msg = new ResponceModel(addedStoreLocation, HttpStatus.CREATED,201, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @GetMapping("/")
    public ResponseEntity<ResponceModel> getAllStoreLocations() {
        List<StoreLocation> storeLocations = storeLocationService.getAllStoreLocations();
        ResponceModel msg = new ResponceModel(storeLocations, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
    
    @PostMapping("/{id}/stock/{stockId}/addQuantity/{quantity}/")
    public ResponseEntity<ResponceModel> addQuantityInStore(@PathVariable Long id, @PathVariable Long stockId, @PathVariable Integer quantity) {
        String result = storeLocationService.addQuantityInStore(quantity, id, stockId);
        ResponceModel msg = new ResponceModel(result, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}

