package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;

import java.util.List;
import java.util.UUID;

public interface TrafficDeviceMaintenanceLogService {

    List<TrafficDeviceMaintenanceLogResponse> findAll();

    TrafficDeviceMaintenanceLogResponse findById(UUID id);

    TrafficDeviceMaintenanceLogResponse save(
            TrafficDeviceMaintenanceLogRequest request);

    void deleteById(UUID id);
}
