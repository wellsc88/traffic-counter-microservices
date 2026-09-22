package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceRepository;
import com.well.tech.traffic.counter.api.service.TrafficDeviceService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrafficDeviceServiceImpl implements TrafficDeviceService {

    private final TrafficDeviceRepository repository;

    public TrafficDeviceServiceImpl(TrafficDeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TrafficDevice> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TrafficDevice> findById(UUID id) {
        return Optional.of(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Traffic device not found: " + id
                )));
    }

    @Override
    public TrafficDevice save(TrafficDevice device) {
        return repository.save(device);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
