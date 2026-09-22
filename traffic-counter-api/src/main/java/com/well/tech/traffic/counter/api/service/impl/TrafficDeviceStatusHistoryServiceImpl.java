package com.well.tech.traffic.counter.api.service.impl;

import com.well.tech.traffic.counter.api.common.exceptions.resource.ResourceNotFoundException;
import com.well.tech.traffic.counter.api.entity.TrafficDeviceStatusHistory;
import com.well.tech.traffic.counter.api.repository.TrafficDeviceStatusHistoryRepository;
import com.well.tech.traffic.counter.api.service.TrafficDeviceStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrafficDeviceStatusHistoryServiceImpl implements TrafficDeviceStatusHistoryService {

    private final TrafficDeviceStatusHistoryRepository repository;

    @Override
    public List<TrafficDeviceStatusHistory> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TrafficDeviceStatusHistory> findById(UUID id) {
        return Optional.of(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Traffic device status history not found: " + id
                )));
    }

    @Override
    public TrafficDeviceStatusHistory save(TrafficDeviceStatusHistory statusHistory) {
        return repository.save(statusHistory);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}