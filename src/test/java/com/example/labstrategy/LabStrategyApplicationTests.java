package com.example.labstrategy;

import com.example.labstrategy.deconz.DeconzClient;
import com.example.labstrategy.deconz.DeconzSensor;
import com.example.labstrategy.sensor.SensorService;
import com.example.labstrategy.sensor.model.HumiditySensor;
import com.example.labstrategy.sensor.model.ImmersionSensor;
import com.example.labstrategy.sensor.model.MotionSensor;
import com.example.labstrategy.sensor.model.TemperatureSensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class LabStrategyApplicationTests {

    @MockBean
    private DeconzClient deconzClient;

    @Qualifier("withoutStrategyService")
    @Autowired
    private SensorService sensorService;

    @Test
    void getSensors_should_return_complete_list_of_sensors() {
        // Given
        given(deconzClient.getSensors()).willReturn(
                List.of(DeconzSensor.builder().state(Map.of("location", "couloir", "temperature", "21.5", "humidity", "0.4")).build(),
                        DeconzSensor.builder().state(Map.of("location", "exterieur", "temperature", "26.2", "humidity", "0.6")).build(),
                        DeconzSensor.builder().name("couloir").state(Map.of("presence", "false")).build(),
                        DeconzSensor.builder().name("fsm").state(Map.of("presence", "true")).build(),
                        DeconzSensor.builder().name("sous-sol").state(Map.of("immersion", "false")).build())
        );

        // When
        var obtainedSensors = sensorService.getSensors();

        // Then
        assertThat(obtainedSensors).satisfiesExactlyInAnyOrder(
                sensor -> {
                    assertThat(sensor).isInstanceOf(TemperatureSensor.class);
                    assertThat(sensor.name()).isEqualTo("couloir");
                    assertThat(sensor.value()).isEqualTo(21.5);
                },
                sensor -> {
                    assertThat(sensor).isInstanceOf(HumiditySensor.class);
                    assertThat(sensor.name()).isEqualTo("couloir");
                    assertThat(sensor.value()).isEqualTo(0.4);
                },
                sensor -> {
                    assertThat(sensor).isInstanceOf(TemperatureSensor.class);
                    assertThat(sensor.name()).isEqualTo("exterieur");
                    assertThat(sensor.value()).isEqualTo(26.2);
                },
                sensor -> {
                    assertThat(sensor).isInstanceOf(HumiditySensor.class);
                    assertThat(sensor.name()).isEqualTo("exterieur");
                    assertThat(sensor.value()).isEqualTo(0.6);
                },
                sensor -> {
                    assertThat(sensor).isInstanceOf(MotionSensor.class);
                    assertThat(sensor.name()).isEqualTo("couloir");
                    assertThat(sensor.value()).isEqualTo(false);
                },
                sensor -> {
                    assertThat(sensor).isInstanceOf(ImmersionSensor.class);
                    assertThat(sensor.name()).isEqualTo("sous-sol");
                    assertThat(sensor.value()).isEqualTo(false);
                }
        );
    }
}
