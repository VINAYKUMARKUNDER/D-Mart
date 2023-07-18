package com.dmart.service;

import java.util.List;

import com.dmart.model.StockMovement;

/**
 * The StockMovementService interface provides methods for managing stock movements.
 */


public interface StockMovementService {

    /**
     * Adds a stock movement entry.
     *
     * @param stockMovement The StockMovement object representing the stock movement to be added.
     * @param <T>           The type of the returned result (specific to the implementation).
     * @return The result of adding the stock movement.
     */
    <T> T addStockMovement(StockMovement stockMovement);

    /**
     * Retrieves all stock movements.
     *
     * @return A List containing all the StockMovement objects.
     */
    List<StockMovement> getAllStockMovements();
}
