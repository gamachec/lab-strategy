package com.example.labstrategy.sensor.extractor.strategy;

import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.extractor.SensorExtractorStrategy;
import com.example.labstrategy.sensor.model.HumiditySensor;
import com.example.labstrategy.sensor.model.Sensor;
import com.example.labstrategy.sensor.model.TemperatureSensor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemperatureSensorExtractorStrategy implements SensorExtractorStrategy {

    @Override
    public boolean support(DeconzSensor deconzSensor) {
        var state = deconzSensor.state();
        return state.containsKey("temperature") && state.containsKey("humidity");
    }

    @Override
    public List<Sensor> extract(DeconzSensor deconzSensor) {
        var state = deconzSensor.state();
        return List.of(TemperatureSensor.builder()
                        .name(state.get("location"))
                        .value(Double.parseDouble(state.get("temperature")))
                        .build(),
                HumiditySensor.builder()
                        .name(state.get("location"))
                        .value(Double.parseDouble(state.get("humidity")))
                        .build()
        );
    }
}
