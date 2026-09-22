package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.entity.TrafficDeviceMaintenanceLog;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceMaintenanceLogRepository;
import com.well.tech.traffic.counter.api.service.TrafficDeviceMaintenanceLogService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrafficDeviceMaintenanceLogServiceImpl implements TrafficDeviceMaintenanceLogService {

    private final TrafficDeviceMaintenanceLogRepository repository;

    public TrafficDeviceMaintenanceLogServiceImpl(TrafficDeviceMaintenanceLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TrafficDeviceMaintenanceLog> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TrafficDeviceMaintenanceLog> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public TrafficDeviceMaintenanceLog save(TrafficDeviceMaintenanceLog device) {
        return repository.save(device);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
