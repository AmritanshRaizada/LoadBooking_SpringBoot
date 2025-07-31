package com.loadbooking.loadbooking.controller;

import com.loadbooking.loadbooking.dto.LoadDTO;
import com.loadbooking.loadbooking.model.LoadStatus;
import com.loadbooking.loadbooking.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/loads")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping
    public ResponseEntity<LoadDTO> createLoad(@RequestBody LoadDTO loadDTO) {
        return ResponseEntity.ok(loadService.createLoad(loadDTO));
    }

    @GetMapping
    public ResponseEntity<Page<LoadDTO>> getLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType,
            @RequestParam(required = false) LoadStatus status,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String destination,
            Pageable pageable) {
        return ResponseEntity.ok(loadService.getLoads(shipperId, truckType, status, origin, destination, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoadDTO> getLoadById(@PathVariable UUID id) {
        LoadDTO loadDTO = loadService.getLoadById(id);
        return loadDTO != null ? ResponseEntity.ok(loadDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoadDTO> updateLoad(@PathVariable UUID id, @RequestBody LoadDTO loadDTO) {
        LoadDTO updatedLoad = loadService.updateLoad(id, loadDTO);
        return updatedLoad != null ? ResponseEntity.ok(updatedLoad) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID id) {
        loadService.deleteLoad(id);
        return ResponseEntity.noContent().build();
    }
}
