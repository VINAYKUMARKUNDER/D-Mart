package com.dmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmart.model.StockMovement;
import com.dmart.service.StockMovementService;
import com.dmart.utlDto.ResponceModel;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v2/stock-movements")
public class StockMovementController {
    private final StockMovementService stockMovementService;

    @Autowired
    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponceModel> addStockMovement(@RequestBody StockMovement stockMovement) {
    	 Object addStockMovement = stockMovementService.addStockMovement(stockMovement);
        ResponceModel msg = new ResponceModel(addStockMovement, HttpStatus.CREATED,201, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(msg);
    }

    @GetMapping("/")
    public ResponseEntity<ResponceModel> getAllStockMovements() {
        List<StockMovement> stockMovements = stockMovementService.getAllStockMovements();
        ResponceModel msg = new ResponceModel(stockMovements, HttpStatus.OK,200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}

