CREATE TABLE traffic_device_maintenance_logs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    device_id UUID NOT NULL,
    maintenance_type VARCHAR(50) NOT NULL,
    ticket_number VARCHAR(50),
    description VARCHAR(500) NOT NULL,
    status VARCHAR(20) NOT NULL,
    maintenance_severity VARCHAR(30) NOT NULL,
    maintenance_priority VARCHAR(30) NOT NULL,
    technician_name VARCHAR(100),
    reason VARCHAR(255),
    planned_at TIMESTAMP WITH TIME ZONE,
    started_at TIMESTAMP WITH TIME ZONE,
    finished_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT fk_maintenance_log_device
        FOREIGN KEY (device_id)
            REFERENCES traffic_devices (id)
);