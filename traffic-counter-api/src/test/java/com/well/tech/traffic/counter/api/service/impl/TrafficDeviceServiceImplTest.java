package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStateRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import com.well.tech.traffic.counter.api.mapper.TrafficDeviceMapper;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrafficDeviceServiceImplTest {

    @Mock
    private TrafficDeviceRepository repository;

    @Mock
    private TrafficDeviceMapper mapper;

    @InjectMocks
    private TrafficDeviceServiceImpl service;

    private UUID id;
    private TrafficDevice device;
    private TrafficDeviceResponse response;
    private TrafficDeviceRequest request;
    private Pageable pageable;
    private TrafficDeviceFilterRequest filter;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();

        device = TrafficDevice.builder()
                .id(id)
                .build();

        response = mock(TrafficDeviceResponse.class);
        request = mock(TrafficDeviceRequest.class);
        filter = mock(TrafficDeviceFilterRequest.class);

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void shouldFindAllTrafficDevices() {
        Page<TrafficDevice> devicePage =
                new PageImpl<>(List.of(device), pageable, 1);

        when(repository.findAll(
                ArgumentMatchers.<Specification<TrafficDevice>>any(),
                eq(pageable)
        )).thenReturn(devicePage);

        when(mapper.toResponse(device))
                .thenReturn(response);

        Page<TrafficDeviceResponse> result =
                service.findAll(filter, pageable);

        assertThat(result)
                .isNotNull()
                .hasSize(1);

        assertThat(result.getContent().getFirst())
                .isEqualTo(response);

        verify(repository)
                .findAll(
                        ArgumentMatchers.<Specification<TrafficDevice>>any(),
                        eq(pageable)
                );

        verify(mapper)
                .toResponse(device);
    }

    @Test
    void shouldFindTrafficDeviceById() {
        when(repository.findById(id))
                .thenReturn(Optional.of(device));

        when(mapper.toResponse(device))
                .thenReturn(response);

        TrafficDeviceResponse result =
                service.findById(id);

        assertThat(result)
                .isEqualTo(response);

        verify(repository)
                .findById(id);

        verify(mapper)
                .toResponse(device);
    }

    @Test
    void shouldThrowExceptionWhenFindingTrafficDeviceById() {
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.findById(id)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device not found with id: " + id
                );

        verify(repository)
                .findById(id);

        verify(mapper, never())
                .toResponse(any());
    }

    @Test
    void shouldSaveTrafficDevice() {
        TrafficDevice savedDevice = TrafficDevice.builder()
                .id(id)
                .build();

        when(mapper.toEntity(request))
                .thenReturn(device);

        when(repository.save(device))
                .thenReturn(savedDevice);

        when(mapper.toResponse(savedDevice))
                .thenReturn(response);

        TrafficDeviceResponse result =
                service.save(request);

        assertThat(result)
                .isEqualTo(response);

        verify(mapper)
                .toEntity(request);

        verify(repository)
                .save(device);

        verify(mapper)
                .toResponse(savedDevice);
    }

    @Test
    void shouldUpdateTrafficDevice() {
        when(repository.findById(id))
                .thenReturn(Optional.of(device));

        when(repository.save(device))
                .thenReturn(device);

        when(mapper.toResponse(device))
                .thenReturn(response);

        TrafficDeviceResponse result =
                service.update(id, request);

        assertThat(result)
                .isEqualTo(response);

        verify(repository)
                .findById(id);

        verify(mapper)
                .updateEntity(device, request);

        verify(repository)
                .save(device);

        verify(mapper)
                .toResponse(device);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingTrafficDevice() {
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.update(id, request)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device not found with id: " + id
                );

        verify(repository)
                .findById(id);

        verify(mapper, never())
                .updateEntity(any(), any());

        verify(repository, never())
                .save(any());
    }

    @Test
    void shouldUpdateTrafficDeviceState() {
        TrafficDeviceStateRequest request =
                mock(TrafficDeviceStateRequest.class);

        when(repository.findById(id))
                .thenReturn(Optional.of(device));

        when(repository.save(device))
                .thenReturn(device);

        when(mapper.toResponse(device))
                .thenReturn(response);

        TrafficDeviceResponse result =
                service.updateState(id, request);

        assertThat(result)
                .isEqualTo(response);

        assertThat(device.getDeviceState())
                .isEqualTo(request.state());

        verify(repository)
                .findById(id);

        verify(repository)
                .save(device);

        verify(mapper)
                .toResponse(device);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingStateOfNonExistingTrafficDevice() {
        TrafficDeviceStateRequest request =
                mock(TrafficDeviceStateRequest.class);

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.updateState(id, request)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device not found with id: " + id
                );

        verify(repository)
                .findById(id);

        verify(repository, never())
                .save(any());

        verify(mapper, never())
                .toResponse(any());
    }

    @Test
    void shouldUpdateTrafficDeviceStatus() {
        TrafficDeviceStatusRequest request =
                mock(TrafficDeviceStatusRequest.class);

        when(repository.findById(id))
                .thenReturn(Optional.of(device));

        when(repository.save(device))
                .thenReturn(device);

        when(mapper.toResponse(device))
                .thenReturn(response);

        TrafficDeviceResponse result =
                service.updateStatus(id, request);

        assertThat(result)
                .isEqualTo(response);

        assertThat(device.getStatus())
                .isEqualTo(request.status());

        verify(repository)
                .findById(id);

        verify(repository)
                .save(device);

        verify(mapper)
                .toResponse(device);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingStatusOfNonExistingTrafficDevice() {
        TrafficDeviceStatusRequest request =
                mock(TrafficDeviceStatusRequest.class);

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.updateStatus(id, request)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device not found with id: " + id
                );

        verify(repository)
                .findById(id);

        verify(repository, never())
                .save(any());

        verify(mapper, never())
                .toResponse(any());
    }

    @Test
    void shouldDeleteTrafficDevice() {
        when(repository.findById(id))
                .thenReturn(Optional.of(device));

        service.deleteById(id);

        verify(repository)
                .findById(id);

        verify(repository)
                .delete(device);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingTrafficDevice() {
        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                service.deleteById(id)
        )
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(
                        "Traffic device not found with id: " + id
                );

        verify(repository)
                .findById(id);

        verify(repository, never())
                .delete(any(TrafficDevice.class));
    }
}