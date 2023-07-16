package com.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmart.model.StockItem;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

}
