package com.example.labstrategy.sensor.model;

public interface Sensor<T> {

    String name();

    T value();
}
