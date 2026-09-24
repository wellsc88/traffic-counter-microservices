package com.well.tech.traffic.counter.api.controller;

import com.well.tech.traffic.counter.api.common.enums.DeviceState;
import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStateRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusRequest;
import com.well.tech.traffic.counter.api.dto.response.PageResponse;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;
import com.well.tech.traffic.counter.api.service.TrafficDeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrafficDeviceControllerTest {

    @Mock
    private TrafficDeviceService service;

    @InjectMocks
    private TrafficDeviceController controller;

    private UUID id;

    private TrafficDeviceFilterRequest filter;

    private TrafficDeviceRequest request;

    private TrafficDeviceStateRequest stateRequest;

    private TrafficDeviceStatusRequest statusRequest;

    private TrafficDeviceResponse response;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();

        filter = new TrafficDeviceFilterRequest(
                "WIM-TH",
                DeviceStatus.ONLINE,
                DeviceType.INDUCTIVE_LOOP,
                Direction.NORTHBOUND,
                DeviceState.ACTIVE,
                "BR-116"
        );

        request = new TrafficDeviceRequest(
                "WIM-TH01",
                "SN-WIM-TH01-001",
                DeviceType.INDUCTIVE_LOOP,
                "Rodovia BR-116 - São Paulo",
                BigDecimal.valueOf(-23.55052000),
                BigDecimal.valueOf(-46.63330800),
                Direction.NORTHBOUND,
                2,
                Map.of(
                        1, Direction.NORTHBOUND,
                        2, Direction.SOUTHBOUND
                ),
                "192.168.10.101",
                "00:1A:2B:3C:4D:01",
                "1.2.0"
        );

        stateRequest = new TrafficDeviceStateRequest(
                DeviceState.ACTIVE
        );

        statusRequest = new TrafficDeviceStatusRequest(
                DeviceStatus.ONLINE
        );

        response = new TrafficDeviceResponse(
                id,
                "WIM-TH01",
                "SN-WIM-TH01-001",
                DeviceType.INDUCTIVE_LOOP,
                DeviceStatus.ONLINE,
                "Rodovia BR-116 - São Paulo",
                BigDecimal.valueOf(-23.55052000),
                BigDecimal.valueOf(-46.63330800),
                Direction.NORTHBOUND,
                2,
                Map.of(
                        1, Direction.NORTHBOUND,
                        2, Direction.SOUTHBOUND
                ),
                "192.168.10.101",
                "00:1A:2B:3C:4D:01",
                "1.2.0",
                DeviceState.ACTIVE,
                Instant.parse("2026-09-24T13:00:00Z"),
                Instant.parse("2026-09-24T13:00:00Z")
        );

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void shouldFindAllTrafficDevices() {
        Page<TrafficDeviceResponse> page =
                new PageImpl<>(List.of(response), pageable, 1);

        when(service.findAll(filter, pageable))
                .thenReturn(page);

        ResponseEntity<PageResponse<TrafficDeviceResponse>> result =
                controller.findAll(filter, pageable);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isNotNull();

        verify(service)
                .findAll(filter, pageable);
    }

    @Test
    void shouldFindTrafficDeviceById() {
        when(service.findById(id))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceResponse> result =
                controller.findById(id);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .findById(id);
    }

    @Test
    void shouldCreateTrafficDevice() {
        when(service.save(request))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceResponse> result =
                controller.save(request);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .save(request);
    }

    @Test
    void shouldUpdateTrafficDevice() {
        when(service.update(id, request))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceResponse> result =
                controller.update(id, request);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .update(id, request);
    }

    @Test
    void shouldUpdateTrafficDeviceState() {
        when(service.updateState(id, stateRequest))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceResponse> result =
                controller.updateState(id, stateRequest);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .updateState(id, stateRequest);
    }

    @Test
    void shouldUpdateTrafficDeviceStatus() {
        when(service.updateStatus(id, statusRequest))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceResponse> result =
                controller.updateStatus(id, statusRequest);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .updateStatus(id, statusRequest);
    }

    @Test
    void shouldDeleteTrafficDevice() {
        ResponseEntity<Void> result =
                controller.deleteById(id);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);

        assertThat(result.getBody())
                .isNull();

        verify(service)
                .deleteById(id);
    }
}