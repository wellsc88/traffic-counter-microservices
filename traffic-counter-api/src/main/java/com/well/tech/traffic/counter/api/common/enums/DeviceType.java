package com.well.tech.traffic.counter.api.common.enums;

public enum DeviceType {
    CAMERA_VISION,  /* Computer Vision/OpenCV */
    INDUCTIVE_LOOP, /* Magnetic Loop in the Pavement */
    RADAR_DOPPLER, /* Microwave Radar */
    INFRARED, /* Optical Beam Sensors */
    LIDAR, /* Optical Beam Sensors */
    SNIFFER /* Counting by MAC Address */
}
