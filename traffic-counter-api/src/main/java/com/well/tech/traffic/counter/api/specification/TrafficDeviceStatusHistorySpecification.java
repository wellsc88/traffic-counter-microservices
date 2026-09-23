package com.well.tech.traffic.counter.api.specification;

import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceStatusHistoryFilterRequest;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@NoArgsConstructor
public final class TrafficDeviceStatusHistorySpecification {

    public static Specification<TrafficDeviceStatusHistory> filter(
            TrafficDeviceStatusHistoryFilterRequest request) {

        Specification<TrafficDeviceStatusHistory> specification =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.conjunction();

        if (request.deviceId() != null) {
            specification = specification.and(
                    deviceIdEquals(request.deviceId())
            );
        }

        if (request.previousStatus() != null) {
            specification = specification.and(
                    previousStatusEquals(request.previousStatus())
            );
        }

        if (request.newStatus() != null) {
            specification = specification.and(
                    newStatusEquals(request.newStatus())
            );
        }

        if (request.changedBy() != null
                && !request.changedBy().isBlank()) {

            specification = specification.and(
                    changedByContains(request.changedBy())
            );
        }

        return specification;
    }

    public static Specification<TrafficDeviceStatusHistory> deviceIdEquals(
            UUID deviceId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("device").get("id"),
                        deviceId
                );
    }

    public static Specification<TrafficDeviceStatusHistory> previousStatusEquals(
            DeviceStatus previousStatus) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("previousStatus"),
                        previousStatus
                );
    }

    public static Specification<TrafficDeviceStatusHistory> newStatusEquals(
            DeviceStatus newStatus) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("newStatus"),
                        newStatus
                );
    }

    public static Specification<TrafficDeviceStatusHistory> changedByContains(
            String changedBy) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("changedBy")),
                        "%" + changedBy.toLowerCase() + "%"
                );
    }
}