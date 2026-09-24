package com.well.tech.traffic.counter.api.mapper;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceMaintenanceLog;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrafficDeviceMaintenanceLogMapperTest {

    private final TrafficDeviceMaintenanceLogMapper mapper =
            new TrafficDeviceMaintenanceLogMapper();

    @Test
    void shouldMapRequestToEntity() {
        TrafficDevice device = TrafficDevice.builder()
                .id(UUID.randomUUID())
                .build();

        TrafficDeviceMaintenanceLogRequest request =
                new TrafficDeviceMaintenanceLogRequest(
                        UUID.randomUUID(),
                        MaintenanceType.PREVENTIVE,
                        "INC-12345",
                        "Firmware update",
                        MaintenanceStatus.IN_PROGRESS,
                        MaintenanceSeverity.NORMAL,
                        MaintenancePriority.LOW,
                        "John Technician",
                        "Scheduled maintenance",
                        Instant.parse("2026-09-25T13:00:00Z"),
                        Instant.parse("2026-09-25T14:00:00Z"),
                        Instant.parse("2026-09-25T15:00:00Z")
                );

        TrafficDeviceMaintenanceLog entity =
                mapper.toEntity(request, device);

        assertEquals(device, entity.getDevice());
        assertEquals(request.maintenanceType(), entity.getMaintenanceType());
        assertEquals(request.ticketNumber(), entity.getTicketNumber());
        assertEquals(request.description(), entity.getDescription());
        assertEquals(request.status(), entity.getStatus());
        assertEquals(request.maintenanceSeverity(), entity.getMaintenanceSeverity());
        assertEquals(request.maintenancePriority(), entity.getMaintenancePriority());
        assertEquals(request.technicianName(), entity.getTechnicianName());
        assertEquals(request.reason(), entity.getReason());
        assertEquals(request.plannedAt(), entity.getPlannedAt());
        assertEquals(request.startedAt(), entity.getStartedAt());
        assertEquals(request.finishedAt(), entity.getFinishedAt());
    }

    @Test
    void shouldMapEntityToResponse() {
        UUID id = UUID.randomUUID();
        UUID deviceId = UUID.randomUUID();

        Instant plannedAt = Instant.parse("2026-09-25T13:00:00Z");
        Instant startedAt = Instant.parse("2026-09-25T14:00:00Z");
        Instant finishedAt = Instant.parse("2026-09-25T15:00:00Z");
        Instant createdAt = Instant.parse("2026-09-24T13:00:00Z");
        Instant updatedAt = Instant.parse("2026-09-24T14:00:00Z");

        TrafficDevice device = TrafficDevice.builder()
                .id(deviceId)
                .build();

        TrafficDeviceMaintenanceLog entity =
                TrafficDeviceMaintenanceLog.builder()
                        .id(id)
                        .device(device)
                        .ticketNumber("INC-12345")
                        .description("Firmware update")
                        .technicianName("John Technician")
                        .reason("Scheduled maintenance")
                        .plannedAt(plannedAt)
                        .startedAt(startedAt)
                        .finishedAt(finishedAt)
                        .createdAt(createdAt)
                        .updatedAt(updatedAt)
                        .build();

        TrafficDeviceMaintenanceLogResponse response =
                mapper.toResponse(entity);

        assertEquals(entity.getId(), response.id());
        assertEquals(entity.getDevice().getId(), response.deviceId());
        assertEquals(entity.getMaintenanceType(), response.maintenanceType());
        assertEquals(entity.getTicketNumber(), response.ticketNumber());
        assertEquals(entity.getDescription(), response.description());
        assertEquals(entity.getStatus(), response.status());
        assertEquals(entity.getMaintenanceSeverity(), response.maintenanceSeverity());
        assertEquals(entity.getMaintenancePriority(), response.maintenancePriority());
        assertEquals(entity.getTechnicianName(), response.technicianName());
        assertEquals(entity.getReason(), response.reason());
        assertEquals(entity.getPlannedAt(), response.plannedAt());
        assertEquals(entity.getStartedAt(), response.startedAt());
        assertEquals(entity.getFinishedAt(), response.finishedAt());
        assertEquals(entity.getCreatedAt(), response.createdAt());
        assertEquals(entity.getUpdatedAt(), response.updatedAt());
    }
}