package com.dmart.utlDto;

import java.util.ArrayList;
import java.util.List;

import com.dmart.model.StockItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreLocationDto {

	
	
	private String name;
    

    private String address;
    

 
    private List<StockItem> stockItems = new ArrayList();
}
