package com.example.labstrategy.sensor.extractor;

import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.model.Sensor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SensorExtractorContext {

    private final Collection<SensorExtractorStrategy> strategies;

    public List<Sensor> selectStrategyAndExtractSensors(DeconzSensor deconzSensor) {
        // possible d'adapter la sélection de la stratégie au besoin, ici on considère qu'il peut y
        // avoir plusieurs stratégies qui match l'objet, on renvoit la liste de tout ce qu'on a réussi à extraire
        return strategies.stream()
                .filter(strategy -> strategy.support(deconzSensor))
                .map(strategy -> strategy.extract(deconzSensor))
                .flatMap(Collection::stream)
                .toList();
    }
}
