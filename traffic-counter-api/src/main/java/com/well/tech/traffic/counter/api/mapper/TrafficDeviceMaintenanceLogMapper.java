package com.well.tech.traffic.counter.api.mapper;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceMaintenanceLog;
import org.springframework.stereotype.Component;

@Component
public class TrafficDeviceMaintenanceLogMapper {

    public TrafficDeviceMaintenanceLog toEntity(
            TrafficDeviceMaintenanceLogRequest request,
            TrafficDevice device) {

        return TrafficDeviceMaintenanceLog.builder()
                .device(device)
                .maintenanceType(request.maintenanceType())
                .ticketNumber(request.ticketNumber())
                .description(request.description())
                .status(request.status())
                .maintenanceSeverity(request.maintenanceSeverity())
                .maintenancePriority(request.maintenancePriority())
                .technicianName(request.technicianName())
                .reason(request.reason())
                .plannedAt(request.plannedAt())
                .startedAt(request.startedAt())
                .finishedAt(request.finishedAt())
                .build();
    }

    public TrafficDeviceMaintenanceLogResponse toResponse(
            TrafficDeviceMaintenanceLog entity) {

        return new TrafficDeviceMaintenanceLogResponse(
                entity.getId(),
                entity.getDevice().getId(),
                entity.getMaintenanceType(),
                entity.getTicketNumber(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getMaintenanceSeverity(),
                entity.getMaintenancePriority(),
                entity.getTechnicianName(),
                entity.getReason(),
                entity.getPlannedAt(),
                entity.getStartedAt(),
                entity.getFinishedAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}