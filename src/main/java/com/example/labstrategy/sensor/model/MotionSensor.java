package com.example.labstrategy.sensor.model;

import lombok.Builder;

@Builder
public record MotionSensor(
        String name,
        Boolean value
) implements Sensor<Boolean> {
}
