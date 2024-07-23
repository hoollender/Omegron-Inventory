package com.omegron.inventory.model.enums.converters;

import com.omegron.inventory.model.enums.TransmissionTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransmissionTypeEnumConverterTest {
    private TransmissionTypeEnumConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TransmissionTypeEnumConverter();
    }

    @Test
    void testConvert_validEnum() {
        // Assuming TransmissionTypeEnum has an enum constant DualClutch
        //The converter removes the white space
        String source = "Dual Clutch";
        TransmissionTypeEnum expected = TransmissionTypeEnum.DualClutch;

        TransmissionTypeEnum result = converter.convert(source);

        assertEquals(expected, result, "The conversion result should be DualClutch");
    }

    @Test
    void testConvert_invalidEnum() {
        String source = "Invalid Transmission";

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