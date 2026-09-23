package com.well.tech.traffic.counter.api.controller;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.PageResponse;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;
import com.well.tech.traffic.counter.api.service.TrafficDeviceMaintenanceLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Traffic Device Maintenance Logs",
        description = "Operations for traffic device maintenance logs"
)
public class TrafficDeviceMaintenanceLogController {

    private final TrafficDeviceMaintenanceLogService service;

    @Operation(
            summary = "List maintenance logs",
            description = "Returns a paginated list of maintenance logs with optional filters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Maintenance logs retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<PageResponse<TrafficDeviceMaintenanceLogResponse>> findAll(
            @ModelAttribute TrafficDeviceMaintenanceLogFilterRequest filter,
            @Parameter(description = "Pagination and sorting parameters")
            Pageable pageable
    ) {

        Page<TrafficDeviceMaintenanceLogResponse> page =
                service.findAll(filter, pageable);

        return ResponseEntity.ok(PageResponse.from(page));
    }

    @Operation(
            summary = "Get maintenance log",
            description = "Returns a maintenance log by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Maintenance log found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Maintenance log not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrafficDeviceMaintenanceLogResponse> findById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Create maintenance log",
            description = "Creates a new maintenance log for a traffic device."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Maintenance log created successfully"
    )
    @PostMapping
    public ResponseEntity<TrafficDeviceMaintenanceLogResponse> save(
            @RequestBody TrafficDeviceMaintenanceLogRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @Operation(
            summary = "Delete maintenance log",
            description = "Deletes a maintenance log by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Maintenance log deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Maintenance log not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable UUID id) {

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}