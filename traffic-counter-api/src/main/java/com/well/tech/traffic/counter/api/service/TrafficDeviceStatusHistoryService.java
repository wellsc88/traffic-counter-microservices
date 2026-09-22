package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TrafficDeviceStatusHistoryService {

    List<TrafficDeviceStatusHistory> findAll();

    Optional<TrafficDeviceStatusHistory> findById(UUID id);

    TrafficDeviceStatusHistory save(TrafficDeviceStatusHistory device);

    void deleteById(UUID id);
}
