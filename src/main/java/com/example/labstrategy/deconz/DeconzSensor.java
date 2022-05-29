package com.example.labstrategy.deconz;

import lombok.Builder;

import java.util.Map;

@Builder
public record DeconzSensor(
        String id,
        String name,
        Map<String, String> state
) {
}
