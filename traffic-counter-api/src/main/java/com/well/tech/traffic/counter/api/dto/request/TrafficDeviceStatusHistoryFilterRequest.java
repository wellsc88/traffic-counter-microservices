package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Filters for searching traffic device status history")
public record TrafficDeviceStatusHistoryFilterRequest(

        @Schema(
                description = "Filters status history by traffic device ID",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID deviceId,

        @Schema(
                description = "Filters records by the previous device status",
                example = "INACTIVE"
        )
        DeviceStatus previousStatus,

        @Schema(
                description = "Filters records by the new device status",
                example = "ACTIVE"
        )
        DeviceStatus newStatus,

        @Schema(
                description = "Filters records by the user or system that changed the status",
                example = "admin"
        )
        String changedBy
) {
}