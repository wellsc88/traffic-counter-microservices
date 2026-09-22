package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;

import java.util.UUID;

public record TrafficDeviceStatusHistoryRequest(
        UUID deviceId,
        DeviceStatus previousStatus,
        DeviceStatus newStatus,
        String reason,
        String changedBy
) {
}
