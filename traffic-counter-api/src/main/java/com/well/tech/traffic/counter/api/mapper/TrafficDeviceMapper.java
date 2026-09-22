package com.well.tech.traffic.counter.api.mapper;

import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceRequest;
import com.well.tech.traffic.counter.api.dto.response.TrafficDeviceResponse;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import org.springframework.stereotype.Component;

@Component
public class TrafficDeviceMapper {

    public TrafficDevice toEntity(TrafficDeviceRequest request) {
        return TrafficDevice.builder()
                .deviceName(request.deviceName())
                .serialNumber(request.serialNumber())
                .deviceType(request.deviceType())
                .location(request.location())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .direction(request.direction())
                .lanesCovered(request.lanesCovered())
                .laneDirections(request.laneDirections())
                .addressIpv4(request.addressIpv4())
                .macAddress(request.macAddress())
                .firmwareVersion(request.firmwareVersion())
                .build();
    }

    public void updateEntity(
            TrafficDevice entity,
            TrafficDeviceRequest request) {

        entity.setDeviceName(request.deviceName());
        entity.setSerialNumber(request.serialNumber());
        entity.setDeviceType(request.deviceType());
        entity.setLocation(request.location());
        entity.setLatitude(request.latitude());
        entity.setLongitude(request.longitude());
        entity.setDirection(request.direction());
        entity.setLanesCovered(request.lanesCovered());
        entity.setLaneDirections(request.laneDirections());
        entity.setAddressIpv4(request.addressIpv4());
        entity.setMacAddress(request.macAddress());
        entity.setFirmwareVersion(request.firmwareVersion());
    }

    public TrafficDeviceResponse toResponse(TrafficDevice entity) {
        return new TrafficDeviceResponse(
                entity.getId(),
                entity.getDeviceName(),
                entity.getSerialNumber(),
                entity.getDeviceType(),
                entity.getStatus(),
                entity.getLocation(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getDirection(),
                entity.getLanesCovered(),
                entity.getLaneDirections(),
                entity.getAddressIpv4(),
                entity.getMacAddress(),
                entity.getFirmwareVersion(),
                entity.getEnabled(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}