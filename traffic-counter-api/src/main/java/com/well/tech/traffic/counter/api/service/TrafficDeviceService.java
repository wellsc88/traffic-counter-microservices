package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TrafficDeviceService {

    Page<TrafficDeviceResponse> findAll(TrafficDeviceFilterRequest filter, Pageable pageable);

    TrafficDeviceResponse findById(UUID id);

    TrafficDeviceResponse save(TrafficDeviceRequest request);

    TrafficDeviceResponse update(
            UUID id,
            TrafficDeviceRequest request);

    void deleteById(UUID id);
}
