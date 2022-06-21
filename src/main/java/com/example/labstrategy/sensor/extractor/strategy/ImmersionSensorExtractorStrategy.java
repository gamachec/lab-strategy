package com.example.labstrategy.sensor.extractor.strategy;

import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.extractor.SensorExtractorStrategy;
import com.example.labstrategy.sensor.model.ImmersionSensor;
import com.example.labstrategy.sensor.model.Sensor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImmersionSensorExtractorStrategy implements SensorExtractorStrategy {

    @Override
    public boolean support(DeconzSensor deconzSensor) {
        var state = deconzSensor.state();
        return state.containsKey("immersion");
    }

    @Override
    public List<Sensor> extract(DeconzSensor deconzSensor) {
        var state = deconzSensor.state();
        return List.of(ImmersionSensor.builder()
                .name(deconzSensor.name())
                .value(Boolean.valueOf(state.get("immersion")))
                .build());
    }
}
