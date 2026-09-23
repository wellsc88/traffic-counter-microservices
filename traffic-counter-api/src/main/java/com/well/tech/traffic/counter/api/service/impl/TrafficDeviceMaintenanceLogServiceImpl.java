package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceMaintenanceLog;
import com.well.tech.traffic.counter.api.mapper.TrafficDeviceMaintenanceLogMapper;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceMaintenanceLogRepository;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceRepository;
import com.well.tech.traffic.counter.api.service.TrafficDeviceMaintenanceLogService;
import com.well.tech.traffic.counter.api.specification.TrafficDeviceMaintenanceLogSpecification;
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
public class TrafficDeviceMaintenanceLogServiceImpl
        implements TrafficDeviceMaintenanceLogService {

    private final TrafficDeviceMaintenanceLogRepository repository;
    private final TrafficDeviceRepository trafficDeviceRepository;
    private final TrafficDeviceMaintenanceLogMapper mapper;

    @Override
    public Page<TrafficDeviceMaintenanceLogResponse> findAll(
            TrafficDeviceMaintenanceLogFilterRequest filter,
            Pageable pageable
    ) {

        log.debug("Finding traffic device maintenance logs with filters");

        Specification<TrafficDeviceMaintenanceLog> specification =
                TrafficDeviceMaintenanceLogSpecification.filter(filter);

        return repository.findAll(specification, pageable)
                .map(mapper::toResponse);

    }

    @Override
    public TrafficDeviceMaintenanceLogResponse findById(UUID id) {
        log.debug("Finding traffic device maintenance log by id: {}", id);

        TrafficDeviceMaintenanceLog entity =
                findTrafficDeviceMaintenanceLogById(id);

        return mapper.toResponse(entity);
    }

    @Override
    public TrafficDeviceMaintenanceLogResponse save(
            TrafficDeviceMaintenanceLogRequest request) {

        log.info("Saving traffic device maintenance log");

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

        TrafficDeviceMaintenanceLog entity =
                mapper.toEntity(request, device);

        TrafficDeviceMaintenanceLog savedEntity =
                repository.save(entity);

        return mapper.toResponse(savedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        log.info(
                "Deleting traffic device maintenance log with id: {}",
                id
        );

        TrafficDeviceMaintenanceLog entity =
                findTrafficDeviceMaintenanceLogById(id);

        repository.delete(entity);
    }

    private TrafficDeviceMaintenanceLog findTrafficDeviceMaintenanceLogById(
            UUID id) {

        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(
                            "Traffic device maintenance log not found with id: {}",
                            id
                    );

                    return new ResourceNotFoundException(
                            "Traffic device maintenance log not found with id: "
                                    + id
                    );
                });
    }
}