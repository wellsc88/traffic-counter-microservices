package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Request for creating a traffic device status history record")
public record TrafficDeviceStatusHistoryRequest(

        @Schema(
                description = "Traffic device unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000"
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
        String changedBy
) {
}