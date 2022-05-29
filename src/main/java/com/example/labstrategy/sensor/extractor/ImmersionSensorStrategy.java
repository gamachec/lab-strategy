package com.example.labstrategy.sensor.extractor;

import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.model.ImmersionSensor;
import com.example.labstrategy.sensor.model.Sensor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImmersionSensorStrategy implements SensorStrategy {
    @Override
    public boolean support(DeconzSensor deconzSensor) {
        return deconzSensor.state().containsKey("immersion");
    }

    @Override
    public List<Sensor<?>> extract(DeconzSensor deconzSensor) {
        return List.of(ImmersionSensor.builder()
                .name(deconzSensor.name())
                .value(Boolean.valueOf(deconzSensor.state().get("immersion")))
                .build());
    }
}
