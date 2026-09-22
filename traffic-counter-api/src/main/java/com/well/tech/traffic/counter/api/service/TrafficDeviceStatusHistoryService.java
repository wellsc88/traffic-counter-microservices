package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;

import java.util.List;
import java.util.UUID;

public interface TrafficDeviceStatusHistoryService {

    List<TrafficDeviceStatusHistoryResponse> findAll();

    TrafficDeviceStatusHistoryResponse findById(UUID id);

    TrafficDeviceStatusHistoryResponse save(
            TrafficDeviceStatusHistoryRequest request);

    void deleteById(UUID id);
}
