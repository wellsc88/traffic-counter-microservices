package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.entity.TrafficDevice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrafficDeviceService {

    List<TrafficDevice> findAll();

    Optional<TrafficDevice> findById(UUID id);

    TrafficDevice save(TrafficDevice device);

    void deleteById(UUID id);
}
