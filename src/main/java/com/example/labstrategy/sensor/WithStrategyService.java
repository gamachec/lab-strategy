package com.example.labstrategy.sensor;

import com.example.labstrategy.deconz.DeconzClient;
import com.example.labstrategy.sensor.model.Sensor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WithStrategyService implements SensorService {

    private final DeconzClient deconzClient;

    @Override
    public List<Sensor> getSensors() {
        return List.of();
    }
}
