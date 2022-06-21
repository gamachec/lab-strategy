package com.example.labstrategy.sensor.extractor.strategy;

import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.extractor.SensorExtractorStrategy;
import com.example.labstrategy.sensor.model.MotionSensor;
import com.example.labstrategy.sensor.model.Sensor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PresenceSensorExtractorStrategy implements SensorExtractorStrategy {

    @Override
    public boolean support(DeconzSensor deconzSensor) {
        var state = deconzSensor.state();
        return state.containsKey("presence") && !"fsm".equals(deconzSensor.name());
    }

    @Override
    public List<Sensor> extract(DeconzSensor deconzSensor) {
        var state = deconzSensor.state();
        return List.of(MotionSensor.builder()
                .name(deconzSensor.name())
                .value(Boolean.valueOf(state.get("presence")))
                .build());
    }
}
