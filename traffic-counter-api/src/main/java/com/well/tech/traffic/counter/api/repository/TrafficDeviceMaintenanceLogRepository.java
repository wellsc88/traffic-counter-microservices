package com.well.tech.traffic.counter.api.repository;

import com.well.tech.traffic.counter.api.entity.TrafficDeviceMaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrafficDeviceMaintenanceLogRepository extends JpaRepository<TrafficDeviceMaintenanceLog, UUID> {
}
