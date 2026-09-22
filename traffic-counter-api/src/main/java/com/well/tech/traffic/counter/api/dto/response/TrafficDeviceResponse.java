package com.well.tech.traffic.counter.api.dto.response;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record TrafficDeviceResponse(
        UUID id,
        String deviceName,
        String serialNumber,
        DeviceType deviceType,
        DeviceStatus status,
        String location,
        BigDecimal latitude,
        BigDecimal longitude,
        Direction direction,
        Integer lanesCovered,
        Map<Integer, Direction> laneDirections,
        String addressIpv4,
        String macAddress,
        String firmwareVersion,
        Boolean enabled,
        Instant createdAt,
        Instant updatedAt
) {
}
