package com.example.labstrategy.sensor.extractor;

import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.model.MotionSensor;
import com.example.labstrategy.sensor.model.Sensor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MotionSensorStrategy implements SensorStrategy {

    @Override
    public boolean support(DeconzSensor deconzSensor) {
        return deconzSensor.state().containsKey("presence") && !"fsm".equals(deconzSensor.name());
    }

    @Override
    public List<Sensor> extract(DeconzSensor deconzSensor) {
        return List.of(MotionSensor.builder()
                .name(deconzSensor.name())
                .value(Boolean.valueOf(deconzSensor.state().get("presence")))
                .build());
    }
}
