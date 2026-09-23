package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Filters for searching traffic device maintenance logs")
public record TrafficDeviceMaintenanceLogFilterRequest(

        @Schema(
                description = "Filters maintenance logs by traffic device ID",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID deviceId,

        @Schema(
                description = "Filters maintenance logs by maintenance type",
                example = "PREVENTIVE"
        )
        MaintenanceType maintenanceType,

        @Schema(
                description = "Filters maintenance logs by current status",
                example = "COMPLETED"
        )
        MaintenanceStatus status,

        @Schema(
                description = "Filters maintenance logs by severity",
                example = "LOW"
        )
        MaintenanceSeverity maintenanceSeverity,

        @Schema(
                description = "Filters maintenance logs by priority",
                example = "MEDIUM"
        )
        MaintenancePriority maintenancePriority,

        @Schema(
                description = "Filters maintenance logs by ticket number using a partial match",
                example = "MNT-2026"
        )
        String ticketNumber,

        @Schema(
                description = "Filters maintenance logs by technician name using a partial match",
                example = "Carlos"
        )
        String technicianName
) {
}