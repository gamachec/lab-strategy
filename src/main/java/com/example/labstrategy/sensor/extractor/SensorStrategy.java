package com.example.labstrategy.sensor.extractor;

import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.model.Sensor;

import java.util.List;

public interface SensorStrategy {
    boolean support(DeconzSensor deconzSensor);

    List<Sensor> extract(DeconzSensor deconzSensor);
}
