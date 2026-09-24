package com.well.tech.traffic.counter.api.controller;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryRequest;
import com.well.tech.traffic.counter.api.dto.response.PageResponse;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceStatusHistoryResponse;
import com.well.tech.traffic.counter.api.service.TrafficDeviceStatusHistoryService;
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

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrafficDeviceStatusHistoryControllerTest {

    @Mock
    private TrafficDeviceStatusHistoryService service;

    @InjectMocks
    private TrafficDeviceStatusHistoryController controller;

    private UUID historyId;

    private TrafficDeviceStatusHistoryFilterRequest filter;
    private TrafficDeviceStatusHistoryRequest request;
    private TrafficDeviceStatusHistoryResponse response;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        UUID deviceId = UUID.randomUUID();
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

        response = new TrafficDeviceStatusHistoryResponse(
                historyId,
                deviceId,
                DeviceStatus.OFFLINE,
                DeviceStatus.ONLINE,
                "Power Recovery",
                "Denis Souza",
                Instant.parse("2026-09-24T13:00:00Z")
        );
    }

    @Test
    void shouldFindAllStatusHistories() {
        Page<TrafficDeviceStatusHistoryResponse> page =
                new PageImpl<>(List.of(response), pageable, 1);

        when(service.findAll(filter, pageable))
                .thenReturn(page);

        ResponseEntity<PageResponse<TrafficDeviceStatusHistoryResponse>> result =
                controller.findAll(filter, pageable);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isNotNull();

        verify(service)
                .findAll(filter, pageable);
    }

    @Test
    void shouldFindStatusHistoryById() {
        when(service.findById(historyId))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceStatusHistoryResponse> result =
                controller.findById(historyId);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .findById(historyId);
    }

    @Test
    void shouldCreateStatusHistory() {
        when(service.save(request))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceStatusHistoryResponse> result =
                controller.save(request);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .save(request);
    }

    @Test
    void shouldDeleteStatusHistory() {
        ResponseEntity<Void> result =
                controller.deleteById(historyId);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);

        assertThat(result.getBody())
                .isNull();

        verify(service)
                .deleteById(historyId);
    }
}