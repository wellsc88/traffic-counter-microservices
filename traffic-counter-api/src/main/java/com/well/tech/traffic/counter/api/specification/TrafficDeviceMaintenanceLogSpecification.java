package com.well.tech.traffic.counter.api.specification;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;
import com.well.tech.traffic.counter.api.dto.request.TrafficDeviceMaintenanceLogFilterRequest;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceMaintenanceLog;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

@NoArgsConstructor
public final class TrafficDeviceMaintenanceLogSpecification {

    public static Specification<TrafficDeviceMaintenanceLog> filter(
            TrafficDeviceMaintenanceLogFilterRequest request) {

        Specification<TrafficDeviceMaintenanceLog> specification =
                (root, query, criteriaBuilder) ->
                        criteriaBuilder.conjunction();

        if (request.deviceId() != null) {
            specification = specification.and(
                    deviceIdEquals(request.deviceId())
            );
        }

        if (request.maintenanceType() != null) {
            specification = specification.and(
                    maintenanceTypeEquals(request.maintenanceType())
            );
        }

        if (request.status() != null) {
            specification = specification.and(
                    statusEquals(request.status())
            );
        }

        if (request.maintenanceSeverity() != null) {
            specification = specification.and(
                    maintenanceSeverityEquals(
                            request.maintenanceSeverity()
                    )
            );
        }

        if (request.maintenancePriority() != null) {
            specification = specification.and(
                    maintenancePriorityEquals(
                            request.maintenancePriority()
                    )
            );
        }

        if (request.ticketNumber() != null
                && !request.ticketNumber().isBlank()) {

            specification = specification.and(
                    ticketNumberContains(request.ticketNumber())
            );
        }

        if (request.technicianName() != null
                && !request.technicianName().isBlank()) {

            specification = specification.and(
                    technicianNameContains(request.technicianName())
            );
        }

        return specification;
    }

    public static Specification<TrafficDeviceMaintenanceLog> deviceIdEquals(
            UUID deviceId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("device").get("id"),
                        deviceId
                );
    }

    public static Specification<TrafficDeviceMaintenanceLog> maintenanceTypeEquals(
            MaintenanceType maintenanceType) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("maintenanceType"),
                        maintenanceType
                );
    }

    public static Specification<TrafficDeviceMaintenanceLog> statusEquals(
            MaintenanceStatus status) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("status"),
                        status
                );
    }

    public static Specification<TrafficDeviceMaintenanceLog> maintenanceSeverityEquals(
            MaintenanceSeverity maintenanceSeverity) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("maintenanceSeverity"),
                        maintenanceSeverity
                );
    }

    public static Specification<TrafficDeviceMaintenanceLog> maintenancePriorityEquals(
            MaintenancePriority maintenancePriority) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("maintenancePriority"),
                        maintenancePriority
                );
    }

    public static Specification<TrafficDeviceMaintenanceLog> ticketNumberContains(
            String ticketNumber) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("ticketNumber")),
                        "%" + ticketNumber.toLowerCase() + "%"
                );
    }

    public static Specification<TrafficDeviceMaintenanceLog> technicianNameContains(
            String technicianName) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("technicianName")),
                        "%" + technicianName.toLowerCase() + "%"
                );
    }
}