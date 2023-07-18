package com.dmart.utlDto;

import com.dmart.model.StoreLocation;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreStockItemDto {
	
	  private Long id;


	    private String StockName;
	    
	    private Integer StockQuantity;
//		private StoreLocation storeLocation;

}
