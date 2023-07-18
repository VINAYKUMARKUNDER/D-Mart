package com.dmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dmart.model.StoreStockItem;

import jakarta.transaction.Transactional;

public interface StoreStockItemRepository extends JpaRepository<StoreStockItem, Long>{
	List<StoreStockItem> getStoreStockItemsByStoreLocationId(Long storeLocationId);
	
	
	@Transactional
    @Modifying
    @Query("UPDATE StoreStockItem s SET s.StockName = :name, s.StockQuantity = :quantity WHERE s.id = :itemId AND s.storeLocation.id = :storeLocationId")
    void updateStoreStockItemByStoreLocationId(Long itemId, String name, Integer quantity, Long storeLocationId);}
