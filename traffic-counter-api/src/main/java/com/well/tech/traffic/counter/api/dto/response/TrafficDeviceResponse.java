package com.well.tech.traffic.counter.api.dto.response;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Schema(description = "Traffic device response")
public record TrafficDeviceResponse(

        @Schema(
                description = "Traffic device unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        UUID id,

        @Schema(
                description = "Traffic device name",
                example = "WIM-TH01"
        )
        String deviceName,

        @Schema(
                description = "Unique device serial number",
                example = "SN-WIM-TH01-001"
        )
        String serialNumber,

        @Schema(
                description = "Type of traffic monitoring device",
                example = "WIM"
        )
        DeviceType deviceType,

        @Schema(
                description = "Current device status",
                example = "ACTIVE"
        )
        DeviceStatus status,

        @Schema(
                description = "Device location",
                example = "Rodovia BR-116 - São Paulo"
        )
        String location,

        @Schema(
                description = "Geographic latitude",
                example = "-23.55052000"
        )
        BigDecimal latitude,

        @Schema(
                description = "Geographic longitude",
                example = "-46.63330800"
        )
        BigDecimal longitude,

        @Schema(
                description = "Traffic direction covered by the device",
                example = "NORTHBOUND"
        )
        Direction direction,

        @Schema(
                description = "Number of lanes covered by the device",
                example = "2"
        )
        Integer lanesCovered,

        @Schema(
                description = "Direction configured for each lane",
                example = "{\"1\": \"NORTHBOUND\", \"2\": \"SOUTHBOUND\"}"
        )
        Map<Integer, Direction> laneDirections,

        @Schema(
                description = "Device IPv4 address",
                example = "192.168.10.101"
        )
        String addressIpv4,

        @Schema(
                description = "Device MAC address",
                example = "00:1A:2B:3C:4D:01"
        )
        String macAddress,

        @Schema(
                description = "Device firmware version",
                example = "1.2.0"
        )
        String firmwareVersion,

        @Schema(
                description = "Indicates whether the device is enabled",
                example = "true"
        )
        Boolean enabled,

        @Schema(
                description = "Record creation date and time",
                example = "2026-09-23T09:00:00Z"
        )
        Instant createdAt,

        @Schema(
                description = "Record last update date and time",
                example = "2026-09-23T10:30:00Z"
        )
        Instant updatedAt
) {
}