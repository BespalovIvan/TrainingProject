package service.impl;

import entity.Car;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarReaderTest {

    @Test
    public void testRead() {
        CarReader carReader = new CarReader("C:\\Test\\JSONTest.json");
        Car carExpected = carReader.read();
        Car carActual = new Car("BMW","M3",90000.4,true);
        assertEquals(carExpected,carActual);
    }
}