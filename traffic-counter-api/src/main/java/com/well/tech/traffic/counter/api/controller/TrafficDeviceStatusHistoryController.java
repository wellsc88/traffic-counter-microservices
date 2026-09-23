package com.well.tech.traffic.counter.api.controller;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;
import com.well.tech.traffic.counter.api.service.TrafficDeviceStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.well.tech.traffic.counter.api.config.ApiVersion.API_BASE_PATH;
import static com.well.tech.traffic.counter.api.config.ApiVersion.API_VERSION;

@RestController
@RequestMapping(API_BASE_PATH + "/" + API_VERSION + "/traffic-device-status-history")
@RequiredArgsConstructor
public class TrafficDeviceStatusHistoryController {

    private final TrafficDeviceStatusHistoryService service;

    @GetMapping
    public ResponseEntity<List<TrafficDeviceStatusHistoryResponse>> findAll(
            @ModelAttribute TrafficDeviceStatusHistoryFilterRequest filter) {

        return ResponseEntity.ok(service.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrafficDeviceStatusHistoryResponse> findById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TrafficDeviceStatusHistoryResponse> save(
            @RequestBody TrafficDeviceStatusHistoryRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable UUID id) {

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}