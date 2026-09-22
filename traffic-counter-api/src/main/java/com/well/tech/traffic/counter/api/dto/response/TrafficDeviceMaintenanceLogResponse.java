package com.well.tech.traffic.counter.api.dto.response;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;

import java.time.Instant;
import java.util.UUID;

public record TrafficDeviceMaintenanceLogResponse(
        UUID id,
        UUID deviceId,
        MaintenanceType maintenanceType,
        String ticketNumber,
        String description,
        MaintenanceStatus status,
        MaintenanceSeverity maintenanceSeverity,
        MaintenancePriority maintenancePriority,
        String technicianName,
        String reason,
        Instant plannedAt,
        Instant startedAt,
        Instant finishedAt,
        Instant createdAt,
        Instant updatedAt
) {
}
