package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;

import java.math.BigDecimal;
import java.util.Map;

public record TrafficDeviceRequest(
        String deviceName,
        String serialNumber,
        DeviceType deviceType,
        String location,
        BigDecimal latitude,
        BigDecimal longitude,
        Direction direction,
        Integer lanesCovered,
        Map<Integer, Direction> laneDirections,
        String addressIpv4,
        String macAddress,
        String firmwareVersion
){
}
