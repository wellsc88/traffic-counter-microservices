package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;

import java.util.UUID;

public record TrafficDeviceMaintenanceLogFilterRequest(
        UUID deviceId,
        MaintenanceType maintenanceType,
        MaintenanceStatus status,
        MaintenanceSeverity maintenanceSeverity,
        MaintenancePriority maintenancePriority,
        String ticketNumber,
        String technicianName
) {
}
