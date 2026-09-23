package com.well.tech.traffic.counter.api.repository;

import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TrafficDeviceStatusHistoryRepository extends JpaRepository<TrafficDeviceStatusHistory, UUID>,
        JpaSpecificationExecutor<TrafficDeviceStatusHistory> {
}
