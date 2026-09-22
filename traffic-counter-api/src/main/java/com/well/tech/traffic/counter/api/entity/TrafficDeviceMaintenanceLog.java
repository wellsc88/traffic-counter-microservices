package com.well.tech.traffic.counter.api.entity;

import com.well.tech.traffic.counter.api.common.enums.MaintenancePriority;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceSeverity;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceStatus;
import com.well.tech.traffic.counter.api.common.enums.MaintenanceType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "traffic_device_maintenance_logs")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrafficDeviceMaintenanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "device_id", nullable = false)
    private TrafficDevice device;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_type", length = 50, nullable = false)
    private MaintenanceType maintenanceType;

    @Column(name = "ticket_number", length = 50)
    private String ticketNumber;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private MaintenanceStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_severity", length = 30, nullable = false)
    private MaintenanceSeverity maintenanceSeverity;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_priority", length = 30, nullable = false)
    private MaintenancePriority maintenancePriority;

    @Column(name = "technician_name", length = 100)
    private String technicianName;

    @Column
    private String reason;

    @Column(name = "planned_at")
    private Instant plannedAt;

    @Column(name = "started_at")
    private Instant startedAt;

    @Column(name = "finished_at")
    private Instant finishedAt;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}