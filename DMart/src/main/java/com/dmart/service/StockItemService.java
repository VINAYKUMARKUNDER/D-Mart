package com.dmart.service;

import java.util.List;

import com.dmart.utlDto.StockItemDto;

/**
 * Service interface for managing stock items.
 */
public interface StockItemService {
  
  /**
   * Adds a new stock item.
   *
   * @param stockItem The stock item to be added.
   * @return The added stock item as a StockItemDto.
   */
  StockItemDto addStockItem(StockItemDto stockItem);

  /**
   * Updates an existing stock item.
   *
   * @param stockItem   The updated stock item data.
   * @param stockItemId The ID of the stock item to be updated.
   * @return The updated stock item as a StockItemDto.
   */
  StockItemDto updateStockItem(StockItemDto stockItem, Long stockItemId);

  /**
   * Deletes a stock item.
   *
   * @param stockItemId The ID of the stock item to be deleted.
   * @return A message indicating the result of the deletion.
   */
  String deleteStockItem(Long stockItemId);

  /**
   * Retrieves a stock item by its ID.
   *
   * @param stockItemId The ID of the stock item to be retrieved.
   * @return The retrieved stock item as a StockItemDto.
   */
  StockItemDto getStockItemById(Long stockItemId);

  /**
   * Retrieves all stock items.
   *
   * @return A list of all stock items as StockItemDto objects.
   */
  List<StockItemDto> getAllStockItems();
}
