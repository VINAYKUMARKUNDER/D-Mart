package com.dmart.service;

import java.util.List;

import com.dmart.model.StoreLocation;

public interface StoreLocationService {
    StoreLocation addStoreLocation(StoreLocation storeLocation);
    List<StoreLocation> getAllStoreLocations();
    StoreLocation findLocationById(Long Id);
    void updateQuantity(StoreLocation location, Long Id);
    String addQuantityInStore(Integer quantity, Long id, Long stockId);
}

