package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.*;

import java.util.List;

public interface InventoryService {

    InventoryResponse create(CreateInventoryRequest request);

    InventoryResponse getById(Long id);

    List<InventoryResponse>  getAll();

    InventoryResponse update(Long id , UpdateInventoryRequest request);

    void reserveStock(ReserveStockRequest request);

    void releaseStock(ReleaseStockRequest request);

    void confirmStock(ConfirmStockRequest request);

    List<StockMovementResponse> getProductId(Long productId);

    InventoryResponse getInventoryByProductId(Long productId);



}
