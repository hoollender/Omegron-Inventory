package com.omegron.inventory.web;

import com.omegron.inventory.OmegronInventoryApplication;
import com.omegron.inventory.model.dto.AddLandLordDTO;
import com.omegron.inventory.model.entity.LandLord;
import com.omegron.inventory.repository.LandLordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = OmegronInventoryApplication.class)
@AutoConfigureMockMvc
public class LandLordControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LandLordRepository landLordRepository;

    @BeforeEach
    void setUp() {
        landLordRepository.deleteAll();
    }

    @Test
    void testGetAllLandLords() throws Exception {
        // Create and save some test landlords
        LandLord landLord1 = new LandLord()
                .setFirstName("Pesho")
                .setMiddleName("Test")
                .setLastName("Peshov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Boris 1")
                .setPersonalNumber("123456789")
                .setPersonalID("2134676")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        LandLord landLord2 = new LandLord()
                .setFirstName("Lilian")
                .setMiddleName("Ivanova")
                .setLastName("Ivanova")
                .setDateOfBirth(LocalDate.of(1985, 2, 2))
                .setAddress("bul Tsarigradsko shose")
                .setPersonalNumber("987654321")
                .setPersonalID("8654654")
                .setValidityID(LocalDate.of(2030, 1, 1))
                .setDateOfIssue(LocalDate.of(2025, 2, 2));

        landLordRepository.save(landLord1);
        landLordRepository.save(landLord2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/landlords/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetLandLordDetails() throws Exception {
        // Create and save a test landlord
        LandLord landLord = new LandLord()
                .setFirstName("Pesho")
                .setMiddleName("Test")
                .setLastName("Peshov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Boris 1")
                .setPersonalNumber("123456789")
                .setPersonalID("2134676")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        landLord = landLordRepository.save(landLord);

        mockMvc.perform(MockMvcRequestBuilders.get("/landlords/details/" + landLord.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Pesho"))
                .andExpect(jsonPath("$.middleName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("Peshov"));
    }

    @Test
    void testAddLandLord() throws Exception {
        AddLandLordDTO addLandLordDTO = new AddLandLordDTO(
                "Pesho",
                "Test",
                "Peshov",
                LocalDate.of(1980, 1, 1),
                "Tsar Boris 1",
                "123456789",
                "2134676",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2020, 1, 1)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/landlords/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "Pesho",
                                    "middleName": "Test",
                                    "lastName": "Peshov",
                                    "dateOfBirth": "1980-01-01",
                                    "address": "Tsar Boris 1",
                                    "personalNumber": "123456789",
                                    "personalID": "2134676",
                                    "validityID": "2025-01-01",
                                    "dateOfIssue": "2020-01-01"
                                }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLandLord() throws Exception {
        // Create and save a test landlord
        LandLord landLord = new LandLord()
                .setFirstName("Pesho")
                .setMiddleName("Test")
                .setLastName("Peshov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Boris 1")
                .setPersonalNumber("123456789")
                .setPersonalID("2134676")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        landLord = landLordRepository.save(landLord);

        mockMvc.perform(MockMvcRequestBuilders.put("/landlords/update/" + landLord.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": %d,
                                    "firstName": "Lilian",
                                    "middleName": "Ivanova",
                                    "lastName": "Ivanova",
                                    "dateOfBirth": "1985-02-02",
                                    "address": "bul Tsarigradsko shose",
                                    "personalNumber": "987654321",
                                    "personalID": "8654654",
                                    "validityID": "2030-01-01",
                                    "dateOfIssue": "2025-02-02"
                                }
                                """.formatted(landLord.getId())))
                .andExpect(status().isNoContent());

        LandLord updatedLandLord = landLordRepository.findById(landLord.getId()).orElseThrow();
        assertEquals("Lilian", updatedLandLord.getFirstName());
        assertEquals("Ivanova", updatedLandLord.getMiddleName());
        assertEquals("Ivanova", updatedLandLord.getLastName());
        assertEquals(LocalDate.of(1985, 2, 2), updatedLandLord.getDateOfBirth());
        assertEquals("bul Tsarigradsko shose", updatedLandLord.getAddress());
        assertEquals("987654321", updatedLandLord.getPersonalNumber());
        assertEquals("8654654", updatedLandLord.getPersonalID());
        assertEquals(LocalDate.of(2030, 1, 1), updatedLandLord.getValidityID());
        assertEquals(LocalDate.of(2025, 2, 2), updatedLandLord.getDateOfIssue());
    }

    @Test
    void testDeleteLandLord() throws Exception {
        // Create and save a test landlord
        LandLord landLord = new LandLord()
                .setFirstName("Pesho")
                .setMiddleName("Test")
                .setLastName("Peshov")
                .setDateOfBirth(LocalDate.of(1980, 1, 1))
                .setAddress("Tsar Boris 1")
                .setPersonalNumber("123456789")
                .setPersonalID("2134676")
                .setValidityID(LocalDate.of(2025, 1, 1))
                .setDateOfIssue(LocalDate.of(2020, 1, 1));

        landLord = landLordRepository.save(landLord);

        mockMvc.perform(MockMvcRequestBuilders.delete("/landlords/" + landLord.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertFalse(landLordRepository.findById(landLord.getId()).isPresent());
    }
}
