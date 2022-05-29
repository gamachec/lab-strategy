package com.example.labstrategy.sensor;

import com.example.labstrategy.deconz.DeconzClient;
import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WithoutStrategyService implements SensorService {

    private final DeconzClient deconzClient;

    @Override
    public List<Sensor<?>> getSensors() {
        var deconzSensors = deconzClient.getSensors();

        return deconzSensors.stream()
                .map(this::convertToSensor)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Sensor<?>> convertToSensor(DeconzSensor deconzSensor) {
        var state = deconzSensor.state();

        if (state.containsKey("temperature") && state.containsKey("humidity")) {
            return List.of(TemperatureSensor.builder()
                            .name(state.get("location"))
                            .value(Double.parseDouble(state.get("temperature")))
                            .build(),
                    HumiditySensor.builder()
                            .name(state.get("location"))
                            .value(Double.parseDouble(state.get("humidity")))
                            .build()
            );

        } else if (state.containsKey("presence") && !"fsm".equals(deconzSensor.name())) {
            return List.of(MotionSensor.builder()
                    .name(deconzSensor.name())
                    .value(Boolean.valueOf(state.get("presence")))
                    .build());

        } else if (state.containsKey("immersion")) {
            return List.of(ImmersionSensor.builder()
                    .name(deconzSensor.name())
                    .value(Boolean.valueOf(state.get("immersion")))
                    .build());
        }
        return List.of();
    }
}
