package com.omegron.inventory.model.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class AddLandLordDTOTest {

    @Test
    void testEmptyAddLandLordDTO() {
        AddLandLordDTO emptyLandLord = AddLandLordDTO.empty();

        assertNull(emptyLandLord.firstName(), "First name should be null");
        assertNull(emptyLandLord.middleName(), "Middle name should be null");
        assertNull(emptyLandLord.lastName(), "Last name should be null");
        assertNull(emptyLandLord.dateOfBirth(), "Date of birth should be null");
        assertNull(emptyLandLord.address(), "Address should be null");
        assertNull(emptyLandLord.personalNumber(), "Personal number should be null");
        assertNull(emptyLandLord.personalID(), "Personal ID should be null");
        assertNull(emptyLandLord.validityID(), "Validity ID should be null");
        assertNull(emptyLandLord.dateOfIssue(), "Date of issue should be null");
    }
}