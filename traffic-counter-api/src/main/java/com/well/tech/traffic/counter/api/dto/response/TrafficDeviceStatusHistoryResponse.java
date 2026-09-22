package com.well.tech.traffic.counter.api.dto.response;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;

import java.time.Instant;
import java.util.UUID;

public record TrafficDeviceStatusHistoryResponse(
        UUID id,
        UUID deviceId,
        DeviceStatus previousStatus,
        DeviceStatus newStatus,
        String reason,
        String changedBy,
        Instant createdAt
) {
}
