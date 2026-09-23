package com.well.tech.traffic.counter.api.dto.response;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Traffic device status history response")
public record TrafficDeviceStatusHistoryResponse(

        @Schema(
                description = "Status history unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID id,

        @Schema(
                description = "Traffic device unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001"
        )
        UUID deviceId,

        @Schema(
                description = "Device status before the change",
                example = "INACTIVE"
        )
        DeviceStatus previousStatus,

        @Schema(
                description = "Device status after the change",
                example = "ACTIVE"
        )
        DeviceStatus newStatus,

        @Schema(
                description = "Reason for the status change",
                example = "Device activated after installation and validation"
        )
        String reason,

        @Schema(
                description = "User or system responsible for the status change",
                example = "admin"
        )
        String changedBy,

        @Schema(
                description = "Date and time when the status change was recorded",
                example = "2026-09-23T10:30:00Z"
        )
        Instant createdAt
) {
}