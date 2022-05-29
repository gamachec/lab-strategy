package com.example.labstrategy.sensor.model;

import lombok.Builder;

@Builder
public record HumiditySensor(
        String name,
        Double value
) implements Sensor<Double> {
}
