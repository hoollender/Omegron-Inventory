package com.omegron.inventory.model.enums.converters;

import com.omegron.inventory.model.enums.ManufacturersEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ManufacturersEnumConverterTest {

    private ManufacturersEnumConverter converter;

    @BeforeEach
    void setUp() {
        converter = new ManufacturersEnumConverter();
    }

    @Test
    void testConvert_validEnum() {
        // Assuming ManufacturersEnum has an enum constant JohnDeere
        //The converter removes the white space
        String source = "John Deere";
        ManufacturersEnum expected = ManufacturersEnum.JohnDeere;

        ManufacturersEnum result = converter.convert(source);

        assertEquals(expected, result, "The conversion result should be JohnDeere");
    }

    @Test
    void testConvert_invalidEnum() {
        String source = "Invalid Manufacturer";

        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(source);
        }, "Converting an invalid enum should throw an IllegalArgumentException");
    }

    @Test
    void testConvert_nullSource() {
        String source = null;

        assertThrows(NullPointerException.class, () -> {
            converter.convert(source);
        }, "Converting a null source should throw a NullPointerException");
    }
}