CREATE TABLE traffic_device_status_history (
      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
      device_id UUID NOT NULL,
      previous_status VARCHAR(30),
      new_status VARCHAR(30) NOT NULL,
      reason VARCHAR(255),
      changed_by VARCHAR(100),
      created_at TIMESTAMP WITH TIME ZONE NOT NULL,

      CONSTRAINT fk_device_status_history_device
          FOREIGN KEY (device_id)
              REFERENCES traffic_devices (id)
);