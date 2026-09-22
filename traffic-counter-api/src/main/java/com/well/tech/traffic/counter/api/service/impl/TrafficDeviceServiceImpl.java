package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.mapper.TrafficDeviceMapper;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceRepository;
import com.well.tech.traffic.counter.api.service.TrafficDeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficDeviceServiceImpl implements TrafficDeviceService {

    private final TrafficDeviceRepository repository;
    private final TrafficDeviceMapper mapper;

    @Override
    public List<TrafficDeviceResponse> findAll() {
        log.debug("Finding all traffic devices");

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public TrafficDeviceResponse findById(UUID id) {
        log.debug("Finding traffic device by id: {}", id);

        TrafficDevice device = findTrafficDeviceById(id);

        return mapper.toResponse(device);
    }

    @Override
    public TrafficDeviceResponse save(TrafficDeviceRequest request) {
        log.info("Saving traffic device");

        TrafficDevice device = mapper.toEntity(request);
        TrafficDevice savedDevice = repository.save(device);

        return mapper.toResponse(savedDevice);
    }

    @Override
    public TrafficDeviceResponse update(
            UUID id,
            TrafficDeviceRequest request) {

        log.info("Updating traffic device with id: {}", id);

        TrafficDevice device = findTrafficDeviceById(id);

        mapper.updateEntity(device, request);

        TrafficDevice updatedDevice = repository.save(device);

        return mapper.toResponse(updatedDevice);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting traffic device with id: {}", id);

        TrafficDevice device = findTrafficDeviceById(id);

        repository.delete(device);
    }

    private TrafficDevice findTrafficDeviceById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Traffic device not found with id: {}", id);

                    return new ResourceNotFoundException(
                            "Traffic device not found with id: " + id
                    );
                });
    }
}