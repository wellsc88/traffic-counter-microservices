package com.well.tech.traffic.counter.api.mapper;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrafficDeviceStatusHistoryMapperTest {

    private final TrafficDeviceStatusHistoryMapper mapper =
            new TrafficDeviceStatusHistoryMapper();

    @Test
    void shouldMapRequestToEntity() {
        TrafficDevice device = TrafficDevice.builder()
                .id(UUID.randomUUID())
                .build();

        TrafficDeviceStatusHistoryRequest request =
                new TrafficDeviceStatusHistoryRequest(
                        UUID.randomUUID(),
                        DeviceStatus.OFFLINE,
                        DeviceStatus.ONLINE,
                        "Power Fail",
                        "Carlos Silva"
                );

        TrafficDeviceStatusHistory entity =
                mapper.toEntity(request, device);

        assertEquals(device, entity.getDevice());
        assertEquals(request.previousStatus(), entity.getPreviousStatus());
        assertEquals(request.newStatus(), entity.getNewStatus());
        assertEquals(request.reason(), entity.getReason());
        assertEquals(request.changedBy(), entity.getChangedBy());
    }

    @Test
    void shouldMapEntityToResponse() {
        UUID historyId = UUID.randomUUID();
        UUID deviceId = UUID.randomUUID();
        Instant createdAt = Instant.parse("2026-09-24T13:00:00Z");

        TrafficDevice device = TrafficDevice.builder()
                .id(deviceId)
                .build();

        TrafficDeviceStatusHistory entity =
                TrafficDeviceStatusHistory.builder()
                        .id(historyId)
                        .device(device)
                        .reason("Status changed")
                        .changedBy("system")
                        .createdAt(createdAt)
                        .build();

        TrafficDeviceStatusHistoryResponse response =
                mapper.toResponse(entity);

        assertEquals(entity.getId(), response.id());
        assertEquals(entity.getDevice().getId(), response.deviceId());
        assertEquals(entity.getPreviousStatus(), response.previousStatus());
        assertEquals(entity.getNewStatus(), response.newStatus());
        assertEquals(entity.getReason(), response.reason());
        assertEquals(entity.getChangedBy(), response.changedBy());
        assertEquals(entity.getCreatedAt(), response.createdAt());
    }
}