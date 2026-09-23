package com.well.tech.traffic.counter.api.specification;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceFilterRequest;
import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public final class TrafficDeviceSpecification {

    public static Specification<TrafficDevice> filter(
            TrafficDeviceFilterRequest request) {

        Specification<TrafficDevice> specification =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.conjunction();

        if (request.deviceName() != null
                && !request.deviceName().isBlank()) {

            specification = specification.and(
                    deviceNameContains(request.deviceName())
            );
        }

        if (request.status() != null) {
            specification = specification.and(
                    statusEquals(request.status())
            );
        }

        if (request.deviceType() != null) {
            specification = specification.and(
                    deviceTypeEquals(request.deviceType())
            );
        }

        if (request.direction() != null) {
            specification = specification.and(
                    directionEquals(request.direction())
            );
        }

        if (request.enabled() != null) {
            specification = specification.and(
                    enabledEquals(request.enabled())
            );
        }

        if (request.location() != null
                && !request.location().isBlank()) {

            specification = specification.and(
                    locationContains(request.location())
            );
        }

        return specification;
    }

    public static Specification<TrafficDevice> deviceNameContains(
            String deviceName) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("deviceName")),
                        "%" + deviceName.toLowerCase() + "%"
                );
    }

    public static Specification<TrafficDevice> statusEquals(
            DeviceStatus status) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("status"),
                        status
                );
    }

    public static Specification<TrafficDevice> deviceTypeEquals(
            DeviceType deviceType) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("deviceType"),
                        deviceType
                );
    }

    public static Specification<TrafficDevice> directionEquals(
            Direction direction) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("direction"),
                        direction
                );
    }

    public static Specification<TrafficDevice> enabledEquals(
            Boolean enabled) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("enabled"),
                        enabled
                );
    }

    public static Specification<TrafficDevice> locationContains(
            String location) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("location")),
                        "%" + location.toLowerCase() + "%"
                );
    }
}