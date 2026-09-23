package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;

public record TrafficDeviceFilterRequest(
        String deviceName,
        DeviceStatus status,
        DeviceType deviceType,
        Direction direction,
        Boolean enabled,
        String location
) {
}
