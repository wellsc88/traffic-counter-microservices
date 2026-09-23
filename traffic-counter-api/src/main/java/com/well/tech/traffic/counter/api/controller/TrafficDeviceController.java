package com.well.tech.traffic.counter.api.controller;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.response.PageResponse;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;
import com.well.tech.traffic.counter.api.service.TrafficDeviceService;
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

@RestController
@RequestMapping("/traffic-devices")
@RequiredArgsConstructor
@Tag(
        name = "Traffic Devices",
        description = "Operations for traffic monitoring devices"
)
public class TrafficDeviceController {

    private final TrafficDeviceService service;

    @Operation(
            summary = "List traffic devices",
            description = "Returns a paginated list of traffic devices with optional filters."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Traffic devices retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<PageResponse<TrafficDeviceResponse>> findAll(
            @ModelAttribute TrafficDeviceFilterRequest request,
            @Parameter(description = "Pagination and sorting parameters")
            Pageable pageable) {

        Page<TrafficDeviceResponse> page =
                service.findAll(request, pageable);

        return ResponseEntity.ok(PageResponse.from(page));
    }

    @Operation(
            summary = "Get traffic device",
            description = "Returns a traffic device by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Traffic device found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Traffic device not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TrafficDeviceResponse> findById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(
            summary = "Create traffic device",
            description = "Creates a new traffic device."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Traffic device created successfully"
    )
    @PostMapping
    public ResponseEntity<TrafficDeviceResponse> save(
            @RequestBody TrafficDeviceRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @Operation(
            summary = "Update traffic device",
            description = "Updates an existing traffic device."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Traffic device updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Traffic device not found"
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TrafficDeviceResponse> update(
            @PathVariable UUID id,
            @RequestBody TrafficDeviceRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @Operation(
            summary = "Delete traffic device",
            description = "Deletes a traffic device by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Traffic device deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Traffic device not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable UUID id) {

        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}