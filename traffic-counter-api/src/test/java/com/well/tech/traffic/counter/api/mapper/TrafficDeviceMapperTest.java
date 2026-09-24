package com.well.tech.traffic.counter.api.mapper;

import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import java.util.UUID;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrafficDeviceMapperTest {

    private final TrafficDeviceMapper mapper = new TrafficDeviceMapper();

    @Test
    void shouldMapRequestToEntity() {
        TrafficDeviceRequest request = createRequest();

        TrafficDevice entity = mapper.toEntity(request);

        assertEquals(request.deviceName(), entity.getDeviceName());
        assertEquals(request.serialNumber(), entity.getSerialNumber());
        assertEquals(request.deviceType(), entity.getDeviceType());
        assertEquals(request.location(), entity.getLocation());
        assertEquals(request.latitude(), entity.getLatitude());
        assertEquals(request.longitude(), entity.getLongitude());
        assertEquals(request.direction(), entity.getDirection());
        assertEquals(request.lanesCovered(), entity.getLanesCovered());
        assertEquals(request.laneDirections(), entity.getLaneDirections());
        assertEquals(request.addressIpv4(), entity.getAddressIpv4());
        assertEquals(request.macAddress(), entity.getMacAddress());
        assertEquals(request.firmwareVersion(), entity.getFirmwareVersion());
    }

    @Test
    void shouldUpdateEntityFromRequest() {
        TrafficDevice entity = TrafficDevice.builder()
                .deviceName("Old Device")
                .serialNumber("OLD-001")
                .build();

        TrafficDeviceRequest request = createRequest();

        mapper.updateEntity(entity, request);

        assertEquals(request.deviceName(), entity.getDeviceName());
        assertEquals(request.serialNumber(), entity.getSerialNumber());
        assertEquals(request.deviceType(), entity.getDeviceType());
        assertEquals(request.location(), entity.getLocation());
        assertEquals(request.latitude(), entity.getLatitude());
        assertEquals(request.longitude(), entity.getLongitude());
        assertEquals(request.direction(), entity.getDirection());
        assertEquals(request.lanesCovered(), entity.getLanesCovered());
        assertEquals(request.laneDirections(), entity.getLaneDirections());
        assertEquals(request.addressIpv4(), entity.getAddressIpv4());
        assertEquals(request.macAddress(), entity.getMacAddress());
        assertEquals(request.firmwareVersion(), entity.getFirmwareVersion());
    }

    @Test
    void shouldMapEntityToResponse() {
        UUID id = UUID.randomUUID();
        Instant createdAt = Instant.parse("2026-09-24T13:00:00Z");
        Instant updatedAt = Instant.parse("2026-09-24T13:30:00Z");

        TrafficDevice entity = TrafficDevice.builder()
                .id(id)
                .deviceName("Traffic Device 01")
                .serialNumber("TD-001")
                .deviceType(DeviceType.INDUCTIVE_LOOP)
                .location("São Paulo")
                .latitude(BigDecimal.valueOf(-23.5505))
                .longitude(BigDecimal.valueOf(-46.6333))
                .lanesCovered(2)
                .laneDirections(Map.of(
                    1, Direction.NORTHBOUND,
                    2, Direction.SOUTHBOUND
                ))
                .addressIpv4("192.168.1.100")
                .macAddress("AA:BB:CC:DD:EE:FF")
                .firmwareVersion("1.2.3")
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        TrafficDeviceResponse response = mapper.toResponse(entity);

        assertEquals(entity.getId(), response.id());
        assertEquals(entity.getDeviceName(), response.deviceName());
        assertEquals(entity.getSerialNumber(), response.serialNumber());
        assertEquals(entity.getDeviceType(), response.deviceType());
        assertEquals(entity.getStatus(), response.status());
        assertEquals(entity.getLocation(), response.location());
        assertEquals(entity.getLatitude(), response.latitude());
        assertEquals(entity.getLongitude(), response.longitude());
        assertEquals(entity.getDirection(), response.direction());
        assertEquals(entity.getLanesCovered(), response.lanesCovered());
        assertEquals(entity.getLaneDirections(), response.laneDirections());
        assertEquals(entity.getAddressIpv4(), response.addressIpv4());
        assertEquals(entity.getMacAddress(), response.macAddress());
        assertEquals(entity.getFirmwareVersion(), response.firmwareVersion());
        assertEquals(entity.getDeviceState(), response.deviceState());
        assertEquals(entity.getCreatedAt(), response.createdAt());
        assertEquals(entity.getUpdatedAt(), response.updatedAt());
    }

    private TrafficDeviceRequest createRequest() {
        return new TrafficDeviceRequest(
                "Traffic Device 01",
                "TD-001",
                DeviceType.INDUCTIVE_LOOP,
                "São Paulo",
                BigDecimal.valueOf(-23.5505),
                BigDecimal.valueOf(-46.6333),
                Direction.NORTHBOUND,
                2,
                Map.of(
                        1, Direction.NORTHBOUND,
                        2, Direction.SOUTHBOUND
                ),
                "192.168.1.100",
                "AA:BB:CC:DD:EE:FF",
                "1.2.3"
        );
    }
}