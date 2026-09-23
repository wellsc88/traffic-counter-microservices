package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TrafficDeviceMaintenanceLogService {

    Page<TrafficDeviceMaintenanceLogResponse> findAll(TrafficDeviceMaintenanceLogFilterRequest filter, Pageable pageable);

    TrafficDeviceMaintenanceLogResponse findById(UUID id);

    TrafficDeviceMaintenanceLogResponse save(
            TrafficDeviceMaintenanceLogRequest request);

    void deleteById(UUID id);
}
