package com.well.tech.traffic.counter.api.repository;

import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrafficDeviceStatusHistoryRepository extends JpaRepository<TrafficDeviceStatusHistory, UUID> {
}
