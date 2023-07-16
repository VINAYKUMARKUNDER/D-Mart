package com.dmart.service;

import java.util.List;

import com.dmart.model.StockMovement;

public interface StockMovementService {
	<T> T addStockMovement(StockMovement stockMovement);
    List<StockMovement> getAllStockMovements();
}

