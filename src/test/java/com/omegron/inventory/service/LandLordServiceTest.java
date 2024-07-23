package com.omegron.inventory.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.omegron.inventory.model.dto.AddLandLordDTO;
import com.omegron.inventory.model.dto.LandLordDTO;
import com.omegron.inventory.model.entity.LandLord;
import com.omegron.inventory.repository.LandLordRepository;
import com.omegron.inventory.service.impl.LandLordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

class LandLordServiceTest {

    @Mock
    private LandLordRepository landLordRepository; // Mock repository

    @InjectMocks
    private LandLordServiceImpl landLordService; // Service under test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    @Test
    void testAddLandLord() {   // Create test data
        AddLandLordDTO addLandLordDTO = new AddLandLordDTO("Ivan", "Ivanov", "Petrov",
                LocalDate.of(1980, 1, 1),
                "Tsar Asen 123", "123456789",
                "646955551", LocalDate.of(2025, 1, 1),
                LocalDate.of(2020, 1, 1));

        LandLord landLord = new LandLord();
        landLord.setId(1L);
        landLord
                .setFirstName("Ivan")
                .setMiddleName("Ivanov")
                .setLastName("Petrov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Asen 123").setPersonalNumber("123456789")
                .setPersonalID("646955551")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        // Define behavior for the mocked repository
        when(landLordRepository.save(any(LandLord.class))).thenReturn(landLord);

        // Call the method under test
        long id = landLordService.addLandLord(addLandLordDTO);

        // Verify the result
        assertEquals(1L, id);
    }

    @Test
    void testUpdateLandLord() {
        LandLord landLord = new LandLord();
        landLord.setId(1L);
        landLord
                .setFirstName("Ivan")
                .setMiddleName("Ivanov")
                .setLastName("Petrov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Asen 123").setPersonalNumber("123456789")
                .setPersonalID("646955551")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        LandLordDTO landLordDTO = new LandLordDTO(1L, "Ivan", "Ivanov", "Petrov", LocalDate.of(1980, 1, 1), "Tsar Asen 123", "123456789", "646955551", LocalDate.of(2025, 1, 1), LocalDate.of(2020, 1, 1));

        when(landLordRepository.findById(1L)).thenReturn(Optional.of(landLord));
        when(landLordRepository.save(any(LandLord.class))).thenReturn(landLord);

        landLordService.updateLandLord(1L, landLordDTO);

        verify(landLordRepository, times(1)).save(landLord);
    }

    @Test
    void testDeleteLandLord() {
        doNothing().when(landLordRepository).deleteById(1L);

        landLordService.deleteLandLord(1L);

        verify(landLordRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetLandLordDetails() {
        LandLord landLord = new LandLord();
        landLord.setId(1L);
        landLord
                .setFirstName("Ivan")
                .setMiddleName("Ivanov")
                .setLastName("Petrov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Asen 123")
                .setPersonalNumber("123456789")
                .setPersonalID("646955551")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        when(landLordRepository.findById(1L)).thenReturn(Optional.of(landLord));

        LandLordDTO result = landLordService.getLandLordDetails(1L);

        assertEquals("Ivan", result.firstName());
        assertEquals("Petrov", result.lastName());
    }

    @Test
    void testGetAllLandLordsSummary() {
        LandLord landLord1 = new LandLord();
        landLord1.setId(1L);
        landLord1
                .setFirstName("Ivan")
                .setMiddleName("Ivanov")
                .setLastName("Petrov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Asen 123")
                .setPersonalNumber("123456789")
                .setPersonalID("646955551")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        LandLord landLord2 = new LandLord();
        landLord2.setId(2L);
        landLord2
                .setFirstName("Yoana")
                .setMiddleName("Georgieva")
                .setLastName("Raikova")
                .setDateOfBirth(LocalDate.of(1985, 2, 2))
                .setAddress("456 Avenue")
                .setPersonalNumber("987654321")
                .setPersonalID("12345678")
                .setValidityID(LocalDate.of(2030, 1, 1))
                .setDateOfIssue(LocalDate.of(2025, 2, 2));

        when(landLordRepository.findAll()).thenReturn(List.of(landLord1, landLord2));

        List<LandLordDTO> result = landLordService.getAllLandLordsSummary();

        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        LandLord landLord = new LandLord();
        landLord.setId(1L);
        landLord
                .setFirstName("Ivan")
                .setMiddleName("Ivanov")
                .setLastName("Petrov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Asen 123")
                .setPersonalNumber("123456789")
                .setPersonalID("646955551")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        when(landLordRepository.findById(1L)).thenReturn(Optional.of(landLord));

        LandLordDTO result = landLordService.findById(1L);

        assertEquals("Ivan", result.firstName());
        assertEquals("Petrov", result.lastName());
    }
}
