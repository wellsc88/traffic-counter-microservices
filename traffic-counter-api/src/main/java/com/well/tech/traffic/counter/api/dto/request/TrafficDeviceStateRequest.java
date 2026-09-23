package com.well.tech.traffic.counter.api.dto.request;

import com.well.tech.traffic.counter.api.common.enums.DeviceState;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request for updating a traffic device state")
public record TrafficDeviceStateRequest(

        @Schema(
                description = "New administrative state of the device",
                example = "ACTIVE"
        )
        DeviceState state
) {
}