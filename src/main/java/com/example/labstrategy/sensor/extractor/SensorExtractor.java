package com.example.labstrategy.sensor.extractor;

import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.model.Sensor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SensorExtractor {

    private final Collection<SensorStrategy> sensorStrategies;

    public List<Sensor<?>> extract(DeconzSensor deconzSensor) {
        return sensorStrategies.stream()
                .filter(sensorStrategy -> sensorStrategy.support(deconzSensor))
                .map(sensorStrategy -> sensorStrategy.extract(deconzSensor))
                .flatMap(Collection::stream)
                .toList();
    }
}
