package service.impl;

import entity.Car;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CarReaderTest {

    @Test
    public void testRead() {
        CarReader carReader = new CarReader("C:\\Test\\JSONTest.json");
        Car carExpected = carReader.read();
        Car carActual = new Car("BMW","M3",90000.4,true);
        assertEquals(carExpected,carActual);
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();
    @Test()
    public void testParseException(){
        CarReader carReader = new CarReader("C:\\Test\\JSONTest.json");
        thrown.expectMessage("Incorrect structure JSON");
        carReader.read();
    }
    @Test
    public void testFileNotFoundException(){
        CarReader carReader = new CarReader(":\\Test\\JSONTest.json");
        thrown.expectMessage("Invalid file path");
        carReader.read();
    }

    @Test
    public void testNullPointerException(){
        CarReader carReader = new CarReader("C:\\Test\\JSONTest.json");
        thrown.expectMessage("Not enough arguments");
        carReader.read();
    }

    @Test
    public void testPathToFileEx(){
        thrown.expectMessage("The path to the file is incorrect. Meaning ");
        CarReader carReader = new CarReader("");
        carReader.read();
    }
    @Test
    public void testClassCastException(){
        CarReader carReader = new CarReader("C:\\Test\\JSONTest.json");
        thrown.expectMessage("Incorrect parameter type");
        carReader.read();
    }
}