package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Map;

@Schema(description = "Request for creating or updating a traffic device")
public record TrafficDeviceRequest(

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
        String firmwareVersion
) {
}