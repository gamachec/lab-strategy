package com.example.labstrategy.sensor;

import com.example.labstrategy.deconz.DeconzClient;
import com.example.labstrategy.sensor.extractor.SensorExtractorContext;
import com.example.labstrategy.sensor.model.Sensor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeconzSensorGatewayAdapterImpl implements SensorGatewayAdapter {

    private final DeconzClient deconzClient;

    private final SensorExtractorContext sensorExtractorContext;

    @Override
    public List<Sensor> getSensors() {
        var deconzSensors = deconzClient.getSensors();

        return deconzSensors.stream()
                .map(sensorExtractorContext::selectStrategyAndExtractSensors)
                .flatMap(Collection::stream)
                .toList();
    }
}
