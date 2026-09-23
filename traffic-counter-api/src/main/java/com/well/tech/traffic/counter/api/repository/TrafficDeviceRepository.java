package com.well.tech.traffic.counter.api.repository;

import com.well.tech.traffic.counter.api.entity.TrafficDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TrafficDeviceRepository extends JpaRepository<TrafficDevice, UUID>,
        JpaSpecificationExecutor<TrafficDevice> {
}
