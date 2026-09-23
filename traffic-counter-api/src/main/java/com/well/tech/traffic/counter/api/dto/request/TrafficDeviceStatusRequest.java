package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request for updating a traffic device status")
public record TrafficDeviceStatusRequest(

        @Schema(
                description = "New operational status of the device",
                example = "ONLINE"
        )
        DeviceStatus status
) {
}