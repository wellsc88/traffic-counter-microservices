package com.well.tech.traffic.counter.api.controller;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.PageResponse;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;
import com.well.tech.traffic.counter.api.service.TrafficDeviceMaintenanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.well.tech.traffic.counter.api.config.ApiVersion.API_BASE_PATH;
import static com.well.tech.traffic.counter.api.config.ApiVersion.API_VERSION;

@RestController
@RequestMapping(API_BASE_PATH + "/" + API_VERSION + "/traffic-device-maintenance-logs")
@RequiredArgsConstructor
public class TrafficDeviceMaintenanceLogController {

    private final TrafficDeviceMaintenanceLogService service;

    @GetMapping
    public ResponseEntity<PageResponse<TrafficDeviceMaintenanceLogResponse>> findAll(
            @ModelAttribute TrafficDeviceMaintenanceLogFilterRequest filter,
            Pageable pageable
            ) {

        Page<TrafficDeviceMaintenanceLogResponse> page =
                service.findAll(filter, pageable);

        return ResponseEntity.ok(PageResponse.from(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrafficDeviceMaintenanceLogResponse> findById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TrafficDeviceMaintenanceLogResponse> save(
            @RequestBody TrafficDeviceMaintenanceLogRequest request) {

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