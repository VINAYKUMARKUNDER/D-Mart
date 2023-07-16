package com.dmart.utlDto;

import java.util.List;

import com.dmart.model.StoreLocation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockItemDto {
	private Long id;
	
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    private Integer quantity;
    

}
