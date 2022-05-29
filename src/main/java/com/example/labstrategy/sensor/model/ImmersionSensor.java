package com.example.labstrategy.sensor.model;

import lombok.Builder;

@Builder
public record ImmersionSensor(
        String name,
        Boolean value
) implements Sensor<Boolean> {
}
