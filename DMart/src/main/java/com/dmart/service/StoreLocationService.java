package com.dmart.service;

import java.util.List;

import com.dmart.model.StoreLocation;
import com.dmart.utlDto.StoreLocationDto;



/**
 * The StoreLocationService interface provides methods for managing store locations.
 */
public interface StoreLocationService {

    /**
     * Adds a new store location.
     *
     * @param StoreLocationDto The StoreLocationDto object representing the store location to be added.
     * @return The added StoreLocationDto object.
     */
    StoreLocationDto addStoreLocation(StoreLocationDto StoreLocationDto);

    /**
     * Retrieves all store locations.
     *
     * @return A List containing all the StoreLocationDto objects.
     */
    List<StoreLocationDto> getAllStoreLocations();

    /**
     * Finds a store location by its ID.
     *
     * @param id The ID of the store location to find.
     * @return The StoreLocationDto object with the specified ID, or null if not found.
     */
    StoreLocation findLocationById(Long id);

  

    

}
