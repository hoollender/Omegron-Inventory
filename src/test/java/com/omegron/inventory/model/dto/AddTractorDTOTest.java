package com.omegron.inventory.model.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class AddTractorDTOTest {

    @Test
    void testEmptyAddTractorDTO() {
        AddTractorDTO emptyTractor = AddTractorDTO.empty();

        assertNull(emptyTractor.manufacturer(), "Manufacturer should be null");
        assertNull(emptyTractor.model(), "Model should be null");
        assertNull(emptyTractor.year(), "Year should be null");
        assertNull(emptyTractor.description(), "Description should be null");
        assertNull(emptyTractor.workHours(), "Work hours should be null");
        assertNull(emptyTractor.imageUrl(), "Image URL should be null");
        assertNull(emptyTractor.engineType(), "Engine type should be null");
        assertNull(emptyTractor.transmissionType(), "Transmission type should be null");
    }
}