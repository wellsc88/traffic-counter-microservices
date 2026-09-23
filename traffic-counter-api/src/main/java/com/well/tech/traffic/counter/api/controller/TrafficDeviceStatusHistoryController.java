package com.well.tech.traffic.counter.api.controller;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.PageResponse;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;
import com.well.tech.traffic.counter.api.service.TrafficDeviceStatusHistoryService;
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
@RequestMapping(API_BASE_PATH + "/" + API_VERSION + "/traffic-device-status-history")
@RequiredArgsConstructor
@Tag(
        name = "Traffic Device Status History",
        description = "Operations for traffic device status history"
)
public class TrafficDeviceStatusHistoryController {

    private final TrafficDeviceStatusHistoryService service;

    @Operation(
            summary = "List status history",
            description = "Returns a paginated list of status history records with optional filters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Status history retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<PageResponse<TrafficDeviceStatusHistoryResponse>> findAll(
            @ModelAttribute TrafficDeviceStatusHistoryFilterRequest filter,
            @Parameter(description = "Pagination and sorting parameters")
            Pageable pageable
    ) {

        Page<TrafficDeviceStatusHistoryResponse> page =
                service.findAll(filter, pageable);

        return ResponseEntity.ok(PageResponse.from(page));
    }

    @Operation(
            summary = "Get status history",
            description = "Returns a status history record by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Status history record found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Status history record not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrafficDeviceStatusHistoryResponse> findById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Create status history",
            description = "Creates a new status history record for a traffic device."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Status history record created successfully"
    )
    @PostMapping
    public ResponseEntity<TrafficDeviceStatusHistoryResponse> save(
            @RequestBody TrafficDeviceStatusHistoryRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @Operation(
            summary = "Delete status history",
            description = "Deletes a status history record by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Status history record deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Status history record not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable UUID id) {

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}