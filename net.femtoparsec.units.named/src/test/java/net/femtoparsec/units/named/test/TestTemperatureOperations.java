package net.femtoparsec.units.named.test;

import net.femtoparsec.units.named.Units;
import net.femtoparsec.units.named.measurement.DeltaTemperature;
import net.femtoparsec.units.named.measurement.Temperature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bastien Aracil
 */
public class TestTemperatureOperations  extends UnitTest {

    @Test
    public void testAddInCelsius1() throws Exception {
        final Temperature t1 = Temperature.CELSIUS_25;
        final DeltaTemperature dt = Units.DELTA_CELSIUS.create(2);
        Assertions.assertEquals(27, t1.add(dt).getValue(), 0.01);

    }

    @Test
    public void testAddInCelsius2() throws Exception {
        final Temperature t1 = Temperature.CELSIUS_25;
        final DeltaTemperature dt = Units.DELTA_KELVIN.create(3);
        Assertions.assertEquals(28, t1.add(dt).getValue(), 0.01);
    }

    @Test
    public void testAddInCelsius3() throws Exception {
        final Temperature t1 = Temperature.CELSIUS_25;
        final DeltaTemperature dt = Units.DELTA_FAHRENHEIT.create(9);
        Assertions.assertEquals(30, t1.add(dt).getValue(), 0.01);
    }
}
