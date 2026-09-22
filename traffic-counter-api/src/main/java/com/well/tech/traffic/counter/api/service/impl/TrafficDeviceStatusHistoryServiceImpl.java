package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;
import com.well.tech.traffic.counter.api.mapper.TrafficDeviceStatusHistoryMapper;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceRepository;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceStatusHistoryRepository;
import com.well.tech.traffic.counter.api.service.TrafficDeviceStatusHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficDeviceStatusHistoryServiceImpl
        implements TrafficDeviceStatusHistoryService {

    private final TrafficDeviceStatusHistoryRepository repository;
    private final TrafficDeviceRepository trafficDeviceRepository;
    private final TrafficDeviceStatusHistoryMapper mapper;

    @Override
    public List<TrafficDeviceStatusHistoryResponse> findAll() {
        log.debug("Finding all traffic device status histories");

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public TrafficDeviceStatusHistoryResponse findById(UUID id) {
        log.debug("Finding traffic device status history by id: {}", id);

        TrafficDeviceStatusHistory entity =
                findTrafficDeviceStatusHistoryById(id);

        return mapper.toResponse(entity);
    }

    @Override
    public TrafficDeviceStatusHistoryResponse save(
            TrafficDeviceStatusHistoryRequest request) {

        log.info("Saving traffic device status history");

        TrafficDevice device = trafficDeviceRepository
                .findById(request.deviceId())
                .orElseThrow(() -> {
                    log.warn(
                            "Traffic device not found with id: {}",
                            request.deviceId()
                    );

                    return new ResourceNotFoundException(
                            "Traffic device not found with id: "
                                    + request.deviceId()
                    );
                });

        TrafficDeviceStatusHistory entity =
                mapper.toEntity(request, device);

        TrafficDeviceStatusHistory savedEntity =
                repository.save(entity);

        return mapper.toResponse(savedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        log.info(
                "Deleting traffic device status history with id: {}",
                id
        );

        TrafficDeviceStatusHistory entity =
                findTrafficDeviceStatusHistoryById(id);

        repository.delete(entity);
    }

    private TrafficDeviceStatusHistory findTrafficDeviceStatusHistoryById(
            UUID id) {

        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(
                            "Traffic device status history not found with id: {}",
                            id
                    );

                    return new ResourceNotFoundException(
                            "Traffic device status history not found with id: "
                                    + id
                    );
                });
    }
}