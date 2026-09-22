package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.entity.TrafficDeviceMaintenanceLog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrafficDeviceMaintenanceLogService {

    List<TrafficDeviceMaintenanceLog> findAll();

    Optional<TrafficDeviceMaintenanceLog> findById(UUID id);

    TrafficDeviceMaintenanceLog save(TrafficDeviceMaintenanceLog device);

    void deleteById(UUID id);
}
