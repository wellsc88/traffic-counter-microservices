package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;
import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceMaintenanceLog;
import com.well.tech.traffic.counter.api.mapper.TrafficDeviceMaintenanceLogMapper;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceMaintenanceLogRepository;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrafficDeviceMaintenanceLogServiceImplTest {

    @Mock
    private TrafficDeviceMaintenanceLogRepository repository;

    @Mock
    private TrafficDeviceRepository trafficDeviceRepository;

    @Mock
    private TrafficDeviceMaintenanceLogMapper mapper;

    @InjectMocks
    private TrafficDeviceMaintenanceLogServiceImpl service;

    private UUID deviceId;
    private UUID maintenanceLogId;

    private TrafficDevice device;
    private TrafficDeviceMaintenanceLog entity;
    private TrafficDeviceMaintenanceLog savedEntity;

    private TrafficDeviceMaintenanceLogFilterRequest filter;
    private TrafficDeviceMaintenanceLogRequest request;
    private TrafficDeviceMaintenanceLogResponse response;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        deviceId = UUID.randomUUID();
        maintenanceLogId = UUID.randomUUID();

        pageable = PageRequest.of(0, 10);

        filter = new TrafficDeviceMaintenanceLogFilterRequest(
                deviceId,
                MaintenanceType.FIRMWARE_UPDATE,
                MaintenanceStatus.PLANNED,
                MaintenanceSeverity.NORMAL,
                MaintenancePriority.LOW,
                "NCFUP-12345",
                "Carlos Silva"
        );

        request = new TrafficDeviceMaintenanceLogRequest(
                deviceId,
                MaintenanceType.FIRMWARE_UPDATE,
                "NCFUP-12345",
                "Firmware Update",
                MaintenanceStatus.PLANNED,
                MaintenanceSeverity.NORMAL,
                MaintenancePriority.LOW,
                "Carlos Silva",
                "Preventive maintenance",
                Instant.parse("2026-09-25T13:00:00Z"),
                Instant.parse("2026-09-25T14:00:00Z"),
                Instant.parse("2026-09-25T15:00:00Z")
        );

        response = new TrafficDeviceMaintenanceLogResponse(
                maintenanceLogId,
                deviceId,
                MaintenanceType.FIRMWARE_UPDATE,
                "NCFUP-12345",
                "Firmware Update",
                MaintenanceStatus.PLANNED,
                MaintenanceSeverity.NORMAL,
                MaintenancePriority.LOW,
                "Carlos Silva",
                "Preventive maintenance",
                Instant.parse("2026-09-25T13:00:00Z"),
                Instant.parse("2026-09-25T14:00:00Z"),
                Instant.parse("2026-09-25T15:00:00Z"),
                Instant.parse("2026-09-25T09:00:00Z"),
                Instant.parse("2026-09-25T09:00:00Z")
        );

        device = new TrafficDevice();
        entity = new TrafficDeviceMaintenanceLog();
        savedEntity = new TrafficDeviceMaintenanceLog();
    }

    @Test
    void shouldFindAllMaintenanceLogs() {
        Page<TrafficDeviceMaintenanceLog> entityPage =
                new PageImpl<>(List.of(entity), pageable, 1);

        when(repository.findAll(
                ArgumentMatchers.<Specification<TrafficDeviceMaintenanceLog>>any(),
                eq(pageable)
        )).thenReturn(entityPage);

        when(mapper.toResponse(entity))
                .thenReturn(response);

        Page<TrafficDeviceMaintenanceLogResponse> result =
                service.findAll(filter, pageable);

        assertThat(result)
                .isNotNull()
                .hasSize(1);

        assertThat(result.getContent().getFirst())
                .isSameAs(response);

        verify(repository)
                .findAll(
                        ArgumentMatchers.<Specification<TrafficDeviceMaintenanceLog>>any(),
                        eq(pageable)
                );

        verify(mapper)
                .toResponse(entity);
    }

    @Test
    void shouldFindMaintenanceLogById() {
        when(repository.findById(maintenanceLogId))
                .thenReturn(Optional.of(entity));

        when(mapper.toResponse(entity))
                .thenReturn(response);

        TrafficDeviceMaintenanceLogResponse result =
                service.findById(maintenanceLogId);

        assertThat(result)
                .isSameAs(response);

        verify(repository)
                .findById(maintenanceLogId);

        verify(mapper)
                .toResponse(entity);
    }

    @Test
    void shouldThrowExceptionWhenMaintenanceLogDoesNotExist() {
        when(repository.findById(maintenanceLogId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> service.findById(maintenanceLogId)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device maintenance log not found with id: "
                                + maintenanceLogId
                );

        verify(repository)
                .findById(maintenanceLogId);

        verifyNoInteractions(mapper);
    }

    @Test
    void shouldSaveMaintenanceLog() {
        when(trafficDeviceRepository.findById(deviceId))
                .thenReturn(Optional.of(device));

        when(mapper.toEntity(request, device))
                .thenReturn(entity);

        when(repository.save(entity))
                .thenReturn(savedEntity);

        when(mapper.toResponse(savedEntity))
                .thenReturn(response);

        TrafficDeviceMaintenanceLogResponse result =
                service.save(request);

        assertThat(result)
                .isSameAs(response);

        verify(trafficDeviceRepository)
                .findById(deviceId);

        verify(mapper)
                .toEntity(request, device);

        verify(repository)
                .save(entity);

        verify(mapper)
                .toResponse(savedEntity);
    }

    @Test
    void shouldThrowExceptionWhenSavingWithNonExistingDevice() {
        when(trafficDeviceRepository.findById(deviceId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.save(request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device not found with id: " + deviceId
                );

        verify(trafficDeviceRepository)
                .findById(deviceId);

        verifyNoInteractions(mapper);
        verifyNoInteractions(repository);
    }

    @Test
    void shouldDeleteMaintenanceLog() {
        when(repository.findById(maintenanceLogId))
                .thenReturn(Optional.of(entity));

        service.deleteById(maintenanceLogId);

        verify(repository)
                .findById(maintenanceLogId);

        verify(repository)
                .delete(entity);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingMaintenanceLog() {
        when(repository.findById(maintenanceLogId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> service.deleteById(maintenanceLogId)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device maintenance log not found with id: "
                                + maintenanceLogId
                );

        verify(repository)
                .findById(maintenanceLogId);

        verify(
                repository,
                never()
        ).delete(any(TrafficDeviceMaintenanceLog.class));
    }
}