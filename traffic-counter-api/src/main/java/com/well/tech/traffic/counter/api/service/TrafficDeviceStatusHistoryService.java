package com.well.tech.traffic.counter.api.service;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TrafficDeviceStatusHistoryService {

    Page<TrafficDeviceStatusHistoryResponse> findAll(TrafficDeviceStatusHistoryFilterRequest filter, Pageable pageable);

    TrafficDeviceStatusHistoryResponse findById(UUID id);

    TrafficDeviceStatusHistoryResponse save(
            TrafficDeviceStatusHistoryRequest request);

    void deleteById(UUID id);
}
