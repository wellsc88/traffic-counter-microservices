package com.well.tech.traffic.counter.api.mapper;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;
import org.springframework.stereotype.Component;

@Component
public class TrafficDeviceStatusHistoryMapper {

    public TrafficDeviceStatusHistory toEntity(
            TrafficDeviceStatusHistoryRequest request,
            TrafficDevice device) {

        return TrafficDeviceStatusHistory.builder()
                .device(device)
                .previousStatus(request.previousStatus())
                .newStatus(request.newStatus())
                .reason(request.reason())
                .changedBy(request.changedBy())
                .build();
    }

    public TrafficDeviceStatusHistoryResponse toResponse(
            TrafficDeviceStatusHistory entity) {

        return new TrafficDeviceStatusHistoryResponse(
                entity.getId(),
                entity.getDevice().getId(),
                entity.getPreviousStatus(),
                entity.getNewStatus(),
                entity.getReason(),
                entity.getChangedBy(),
                entity.getCreatedAt()
        );
    }
}