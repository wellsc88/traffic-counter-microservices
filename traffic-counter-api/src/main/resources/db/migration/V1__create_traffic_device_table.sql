CREATE TABLE traffic_devices (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    device_name VARCHAR(255) NOT NULL,
    serial_number VARCHAR(255) UNIQUE,
    device_type VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    location VARCHAR(255) NOT NULL,
    latitude NUMERIC(10, 8),
    longitude NUMERIC(10, 8),
    direction VARCHAR(255) NOT NULL,
    lanes_covered INTEGER,
    address_ipv4 VARCHAR(15),
    mac_address VARCHAR(17),
    firmware_version VARCHAR(50),
    device_state VARCHAR(10) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE traffic_device_lanes (
 device_id UUID NOT NULL,
 lane_number INTEGER NOT NULL,
 direction VARCHAR(255) NOT NULL,
 CONSTRAINT pk_tb_traffic_device_lanes
     PRIMARY KEY (device_id, lane_number),

 CONSTRAINT fk_traffic_device_lanes_device
     FOREIGN KEY (device_id)
         REFERENCES traffic_devices (id)
         ON DELETE CASCADE
);