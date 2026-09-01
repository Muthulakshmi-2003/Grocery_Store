package com.example.orderservice.client;

import com.example.orderservice.dto.ConfirmStockRequest;
import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.ReleaseStockRequest;
import com.example.orderservice.dto.ReserveStockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Inventory-Service")
public interface InventoryClient {


    @GetMapping("/inventory/product/{productId}/inventory")
    InventoryResponse getInventory(@PathVariable Long productId);

    @PostMapping("/inventory/reserve")
    void reserveStock(ReserveStockRequest request);

    @PostMapping("/inventory/release")
    void releaseStock(@RequestBody ReleaseStockRequest request);

    @PostMapping("/inventory/confirm")
    void confirmStock(@RequestBody ConfirmStockRequest request);

}
