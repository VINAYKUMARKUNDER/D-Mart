package com.dmart.service;

import com.dmart.model.StoreLocation;
import com.dmart.model.StoreStockItem;
import com.dmart.utlDto.StoreLocationDto;
import com.dmart.utlDto.StoreStockItemDto;

import java.util.List;

/**
 * The StoreStockItemService interface provides operations to manage store stock items.
 */
public interface StoreStockItemService {

    /**
     * Creates a new store stock item.
     *
     * @param StoreStockItemDto The store stock item to create.
     * @return The created store stock item.
     */
    StoreStockItemDto createStoreStockItem(StoreStockItemDto StoreStockItemDto);

    /**
     * Retrieves a store stock item by its ID.
     *
     * @param id The ID of the store stock item.
     * @return The store stock item if found, or null if not found.
     */
    StoreStockItem getStoreStockItemById(Long id);

    /**
     * Retrieves all store stock items.
     *
     * @return A list of all store stock items.
     */
    List<StoreStockItemDto> getAllStoreStockItems();

    /**
     * Updates an existing store stock item.
     *
     * @param StoreStockItemDto The store stock item to update.
     * @return The updated store stock item.
     */
    StoreStockItemDto updateStoreStockItem(StoreStockItemDto StoreStockItemDto,Long id,Long storeLocationId);

    /**
     * Deletes a store stock item by its ID.
     *
     * @param id The ID of the store stock item to delete.
     */
    void deleteStoreStockItem(Long id);
    
    
    
    List<StoreStockItemDto> getStoreStockItemsByStoreLocationId(Long storeLocationId);
    
    /**
     * Adds quantity of a product in a store location.
     *
     * @param quantity The quantity to be added.
     * @param id       The ID of the store location.
     * @param stockId  The ID of the stock.
     * @return A message indicating the success or failure of adding the quantity in the store location.
     */
    String addQuantityInStore(Integer quantity, Long id, Long stockId);
    
    /**
     * Updates the quantity of a store location.
     *
     * @param location The StoreLocationDto object representing the location to update.
     * @param id       The ID of the store location to update.
     */
    void updateQuantity(StoreLocationDto location, Long id);
}
