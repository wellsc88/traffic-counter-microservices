package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Filters for searching traffic devices")
public record TrafficDeviceFilterRequest(

        @Schema(
                description = "Filters devices by name using a partial match",
                example = "WIM-TH"
        )
        String deviceName,

        @Schema(
                description = "Filters devices by current status",
                example = "ACTIVE"
        )
        DeviceStatus status,

        @Schema(
                description = "Filters devices by device type",
                example = "WIM"
        )
        DeviceType deviceType,

        @Schema(
                description = "Filters devices by traffic direction",
                example = "NORTHBOUND"
        )
        Direction direction,

        @Schema(
                description = "Filters devices by enabled state",
                example = "true"
        )
        Boolean enabled,

        @Schema(
                description = "Filters devices by location using a partial match",
                example = "BR-116"
        )
        String location
) {
}