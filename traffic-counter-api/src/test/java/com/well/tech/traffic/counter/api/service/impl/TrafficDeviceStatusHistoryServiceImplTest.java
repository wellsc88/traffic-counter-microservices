package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;
import com.well.tech.traffic.counter.api.mapper.TrafficDeviceStatusHistoryMapper;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceRepository;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceStatusHistoryRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrafficDeviceStatusHistoryServiceImplTest {

    @Mock
    private TrafficDeviceStatusHistoryRepository repository;

    @Mock
    private TrafficDeviceRepository trafficDeviceRepository;

    @Mock
    private TrafficDeviceStatusHistoryMapper mapper;

    @InjectMocks
    private TrafficDeviceStatusHistoryServiceImpl service;

    private UUID deviceId;
    private UUID historyId;

    private TrafficDevice device;
    private TrafficDeviceStatusHistory entity;
    private TrafficDeviceStatusHistory savedEntity;

    private TrafficDeviceStatusHistoryFilterRequest filter;
    private TrafficDeviceStatusHistoryRequest request;
    private TrafficDeviceStatusHistoryResponse response;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        deviceId = UUID.randomUUID();
        historyId = UUID.randomUUID();

        pageable = PageRequest.of(0, 10);

        filter = new TrafficDeviceStatusHistoryFilterRequest(
                deviceId,
                DeviceStatus.OFFLINE,
                DeviceStatus.ONLINE,
                "Denis Souza"
        );

        request = new TrafficDeviceStatusHistoryRequest(
                deviceId,
                DeviceStatus.OFFLINE,
                DeviceStatus.ONLINE,
                "Power Recovery",
                "Denis Souza"
        );

        device = new TrafficDevice();

        entity = new TrafficDeviceStatusHistory();

        savedEntity = new TrafficDeviceStatusHistory();

        response = new TrafficDeviceStatusHistoryResponse(
                historyId,
                deviceId,
                DeviceStatus.OFFLINE,
                DeviceStatus.ONLINE,
                "Power Recovery",
                "Denis Souza",
                Instant.parse("2026-09-25T13:00:00Z")
        );
    }

    @Test
    void shouldFindAllStatusHistories() {
        Page<TrafficDeviceStatusHistory> entityPage =
                new PageImpl<>(List.of(entity), pageable, 1);

        when(repository.findAll(
                ArgumentMatchers
                        .<Specification<TrafficDeviceStatusHistory>>any(),
                eq(pageable)
        )).thenReturn(entityPage);

        when(mapper.toResponse(entity))
                .thenReturn(response);

        Page<TrafficDeviceStatusHistoryResponse> result =
                service.findAll(filter, pageable);

        assertThat(result)
                .isNotNull()
                .hasSize(1);

        assertThat(result.getContent().getFirst())
                .isSameAs(response);

        verify(repository)
                .findAll(
                        ArgumentMatchers
                                .<Specification<TrafficDeviceStatusHistory>>any(),
                        eq(pageable)
                );

        verify(mapper)
                .toResponse(entity);
    }

    @Test
    void shouldFindStatusHistoryById() {
        when(repository.findById(historyId))
                .thenReturn(Optional.of(entity));

        when(mapper.toResponse(entity))
                .thenReturn(response);

        TrafficDeviceStatusHistoryResponse result =
                service.findById(historyId);

        assertThat(result)
                .isSameAs(response);

        verify(repository)
                .findById(historyId);

        verify(mapper)
                .toResponse(entity);
    }

    @Test
    void shouldThrowExceptionWhenStatusHistoryDoesNotExist() {
        when(repository.findById(historyId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.findById(historyId)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device status history not found with id: "
                                + historyId
                );

        verify(repository)
                .findById(historyId);

        verify(mapper, never())
                .toResponse(any());
    }

    @Test
    void shouldSaveStatusHistory() {
        when(trafficDeviceRepository.findById(deviceId))
                .thenReturn(Optional.of(device));

        when(mapper.toEntity(request, device))
                .thenReturn(entity);

        when(repository.save(entity))
                .thenReturn(savedEntity);

        when(mapper.toResponse(savedEntity))
                .thenReturn(response);

        TrafficDeviceStatusHistoryResponse result =
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

        assertThatThrownBy(() ->
                service.save(request)
        )
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
    void shouldDeleteStatusHistory() {
        when(repository.findById(historyId))
                .thenReturn(Optional.of(entity));

        service.deleteById(historyId);

        verify(repository)
                .findById(historyId);

        verify(repository)
                .delete(entity);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingStatusHistory() {
        when(repository.findById(historyId))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.deleteById(historyId)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device status history not found with id: "
                                + historyId
                );

        verify(repository)
                .findById(historyId);

        verify(repository, never())
                .delete(any(TrafficDeviceStatusHistory.class));
    }
}