package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;

import java.util.List;
import java.util.UUID;

public interface TrafficDeviceService {

    List<TrafficDeviceResponse> findAll();

    TrafficDeviceResponse findById(UUID id);

    TrafficDeviceResponse save(TrafficDeviceRequest request);

    TrafficDeviceResponse update(
            UUID id,
            TrafficDeviceRequest request);

    void deleteById(UUID id);
}
