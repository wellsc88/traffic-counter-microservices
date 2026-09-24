package com.well.tech.traffic.counter.api.controller;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogFilterRequest;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogRequest;
import com.well.tech.traffic.counter.api.dto.response.PageResponse;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceMaintenanceLogResponse;
import com.well.tech.traffic.counter.api.service.TrafficDeviceMaintenanceLogService;
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
class TrafficDeviceMaintenanceLogControllerTest {

    @Mock
    private TrafficDeviceMaintenanceLogService service;

    @InjectMocks
    private TrafficDeviceMaintenanceLogController controller;

    private UUID maintenanceLogId;

    private TrafficDeviceMaintenanceLogFilterRequest filter;
    private TrafficDeviceMaintenanceLogRequest request;
    private TrafficDeviceMaintenanceLogResponse response;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        UUID deviceId = UUID.randomUUID();
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
    }

    @Test
    void shouldFindAllMaintenanceLogs() {
        Page<TrafficDeviceMaintenanceLogResponse> page =
                new PageImpl<>(List.of(response), pageable, 1);

        when(service.findAll(filter, pageable))
                .thenReturn(page);

        ResponseEntity<PageResponse<TrafficDeviceMaintenanceLogResponse>> result =
                controller.findAll(filter, pageable);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isNotNull();

        verify(service)
                .findAll(filter, pageable);
    }

    @Test
    void shouldFindMaintenanceLogById() {
        when(service.findById(maintenanceLogId))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceMaintenanceLogResponse> result =
                controller.findById(maintenanceLogId);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .findById(maintenanceLogId);
    }

    @Test
    void shouldCreateMaintenanceLog() {
        when(service.save(request))
                .thenReturn(response);

        ResponseEntity<TrafficDeviceMaintenanceLogResponse> result =
                controller.save(request);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);

        assertThat(result.getBody())
                .isSameAs(response);

        verify(service)
                .save(request);
    }

    @Test
    void shouldDeleteMaintenanceLog() {
        ResponseEntity<Void> result =
                controller.deleteById(maintenanceLogId);

        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);

        assertThat(result.getBody())
                .isNull();

        verify(service)
                .deleteById(maintenanceLogId);
    }
}