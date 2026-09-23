package com.well.tech.traffic.counter.api.entity;

import com.well.tech.traffic.counter.api.common.enums.DeviceState;
import com.well.tech.traffic.counter.api.common.enums.DeviceStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.well.tech.traffic.counter.api.common.enums.DeviceType;
import com.well.tech.traffic.counter.api.common.enums.Direction;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "traffic_devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrafficDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="device_name", nullable = false)
    private String deviceName;

    @Column(name="serial_number", unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="device_type", length = 30, nullable = false)
    private DeviceType deviceType;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private DeviceStatus status = DeviceStatus.OFFLINE;

    @Column(nullable = false)
    private String location;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 8)
    private BigDecimal longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction;

    @Column(name = "lanes_covered")
    private Integer lanesCovered;

    @ElementCollection
    @CollectionTable(
            name = "traffic_device_lanes",
            joinColumns = @JoinColumn(name = "device_id")
    )
    @MapKeyColumn(name = "lane_number")
    @Column(name = "direction", nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Map<Integer, Direction> laneDirections = new HashMap<>();

    @Column(name="address_ipv4", length = 15)
    private String addressIpv4;

    @Column(name="mac_address", length = 17)
    private String macAddress;

    @Column(name="firmware_version", length = 50)
    private String firmwareVersion;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name="device_state", nullable = false, length = 10)
    private DeviceState deviceState = DeviceState.INACTIVE;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;
}