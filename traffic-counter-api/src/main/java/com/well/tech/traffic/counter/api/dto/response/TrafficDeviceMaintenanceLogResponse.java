package com.well.tech.traffic.counter.api.dto.response;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Traffic device maintenance log response")
public record TrafficDeviceMaintenanceLogResponse(

        @Schema(
                description = "Maintenance log unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID id,

        @Schema(
                description = "Traffic device unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001"
        )
        UUID deviceId,

        @Schema(
                description = "Type of maintenance performed",
                example = "PREVENTIVE"
        )
        MaintenanceType maintenanceType,

        @Schema(
                description = "Maintenance ticket number",
                example = "MNT-2026-0001"
        )
        String ticketNumber,

        @Schema(
                description = "Maintenance description",
                example = "Preventive maintenance of WIM sensor"
        )
        String description,

        @Schema(
                description = "Current maintenance status",
                example = "COMPLETED"
        )
        MaintenanceStatus status,

        @Schema(
                description = "Maintenance severity",
                example = "LOW"
        )
        MaintenanceSeverity maintenanceSeverity,

        @Schema(
                description = "Maintenance priority",
                example = "MEDIUM"
        )
        MaintenancePriority maintenancePriority,

        @Schema(
                description = "Name of the technician responsible for the maintenance",
                example = "Carlos Silva"
        )
        String technicianName,

        @Schema(
                description = "Reason for the maintenance",
                example = "Scheduled preventive maintenance"
        )
        String reason,

        @Schema(
                description = "Planned maintenance date and time",
                example = "2026-09-23T10:00:00Z"
        )
        Instant plannedAt,

        @Schema(
                description = "Maintenance start date and time",
                example = "2026-09-23T10:30:00Z"
        )
        Instant startedAt,

        @Schema(
                description = "Maintenance completion date and time",
                example = "2026-09-23T12:30:00Z"
        )
        Instant finishedAt,

        @Schema(
                description = "Record creation date and time",
                example = "2026-09-23T09:00:00Z"
        )
        Instant createdAt,

        @Schema(
                description = "Record last update date and time",
                example = "2026-09-23T12:30:00Z"
        )
        Instant updatedAt
) {
}