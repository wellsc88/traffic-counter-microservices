package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.mapper.TrafficDeviceMapper;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceRepository;
import com.well.tech.traffic.counter.api.service.TrafficDeviceService;
import com.well.tech.traffic.counter.api.specification.TrafficDeviceSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrafficDeviceServiceImpl implements TrafficDeviceService {

    private final TrafficDeviceRepository repository;
    private final TrafficDeviceMapper mapper;

    @Override
    public Page<TrafficDeviceResponse> findAll(
            TrafficDeviceFilterRequest filter,
            Pageable pageable
    ) {

        log.debug("Finding traffic devices with filters");

        Specification<TrafficDevice> specification =
                TrafficDeviceSpecification.filter(filter);

        return repository.findAll(specification, pageable)
                  .map(mapper::toResponse);
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