package com.omegron.inventory.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.omegron.inventory.model.dto.AddTractorDTO;
import com.omegron.inventory.model.enums.EngineTypeEnum;
import com.omegron.inventory.model.enums.ManufacturersEnum;
import com.omegron.inventory.model.enums.TransmissionTypeEnum;
import com.omegron.inventory.repository.TractorRepository;
import com.omegron.inventory.model.entity.Tractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@AutoConfigureMockMvc
public class TractorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TractorRepository tractorRepository;

    @Test
    void testGetAllTractors() throws Exception {
        // Create and save some test tractors
        Tractor tractor1 = new Tractor()
                .setManufacturer(ManufacturersEnum.JohnDeere)
                .setModel("Model1")
                .setYear(2020)
                .setDescription("Test description 1")
                .setWorkHours(100)
                .setImageUrl("http://image1.com")
                .setEngine(EngineTypeEnum.Diesel)
                .setTransmission(TransmissionTypeEnum.CVT);

        Tractor tractor2 = new Tractor()
                .setManufacturer(ManufacturersEnum.Kubota)
                .setModel("Model2")
                .setYear(2021)
                .setDescription("Test description 2")
                .setWorkHours(200)
                .setImageUrl("http://image2.com")
                .setEngine(EngineTypeEnum.Petrol)
                .setTransmission(TransmissionTypeEnum.DualClutch);

        tractorRepository.save(tractor1);
        tractorRepository.save(tractor2);

        mockMvc.perform(get("/tractors/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(tractorRepository.count()));
    }

    @Test
    void testGetTractorDetails() throws Exception {
        // Create and save a test tractor
        Tractor tractor = new Tractor()
                .setManufacturer(ManufacturersEnum.JohnDeere)
                .setModel("Model1")
                .setYear(2020)
                .setDescription("Test description")
                .setWorkHours(100)
                .setImageUrl("http://image.com")
                .setEngine(EngineTypeEnum.Diesel)
                .setTransmission(TransmissionTypeEnum.CVT);

        tractor = tractorRepository.save(tractor);

        mockMvc.perform(get("/tractors/details/" + tractor.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.manufacturer").value("JohnDeere"))
                .andExpect(jsonPath("$.model").value(tractor.getModel()))
                .andExpect(jsonPath("$.year").value(tractor.getYear()))
                .andExpect(jsonPath("$.description").value(tractor.getDescription()))
                .andExpect(jsonPath("$.workHours").value(tractor.getWorkHours()))
                .andExpect(jsonPath("$.imageUrl").value(tractor.getImageUrl()))
                .andExpect(jsonPath("$.engineType").value(tractor.getEngine().name()))
                .andExpect(jsonPath("$.transmissionType").value(tractor.getTransmission().name()));
    }

    @Test
    void testAddTractor() throws Exception {
        AddTractorDTO addTractorDTO = new AddTractorDTO(
                ManufacturersEnum.JohnDeere,
                "Model1",
                2020,
                "Test description",
                100,
                "http://image.com",
                EngineTypeEnum.Diesel,
                TransmissionTypeEnum.CVT
        );

        mockMvc.perform(post("/tractors/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "manufacturer": "JohnDeere",
                                    "model": "Model1",
                                    "year": 2020,
                                    "description": "Test description",
                                    "workHours": 100,
                                    "imageUrl": "http://image.com",
                                    "engineType": "Diesel",
                                    "transmissionType": "CVT"
                                }
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTractor() throws Exception {
        // Create and save a test tractor
        Tractor tractor = new Tractor()
                .setManufacturer(ManufacturersEnum.JohnDeere)
                .setModel("Model1")
                .setYear(2020)
                .setDescription("Test description")
                .setWorkHours(100)
                .setImageUrl("http://image.com")
                .setEngine(EngineTypeEnum.Diesel)
                .setTransmission(TransmissionTypeEnum.CVT);

        tractor = tractorRepository.save(tractor);

        mockMvc.perform(put("/tractors/update/" + tractor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "manufacturer": "Kubota",
                                    "model": "Model2",
                                    "year": 2021,
                                    "description": "Updated description",
                                    "workHours": 200,
                                    "imageUrl": "http://updatedimage.com",
                                    "engineType": "Petrol",
                                    "transmissionType": "DualClutch"
                                }
                                """.formatted(tractor.getId())))
                .andExpect(status().isNoContent());

        Tractor updatedTractor = tractorRepository.findById(tractor.getId()).orElseThrow();
        assertEquals("Kubota", updatedTractor.getManufacturer().toString());
        assertEquals("Model2", updatedTractor.getModel());
        assertEquals(2021, updatedTractor.getYear());
        assertEquals("Updated description", updatedTractor.getDescription());
        assertEquals(200, updatedTractor.getWorkHours());
        assertEquals("http://updatedimage.com", updatedTractor.getImageUrl());
        assertEquals(EngineTypeEnum.Petrol, updatedTractor.getEngine());
        assertEquals(TransmissionTypeEnum.DualClutch, updatedTractor.getTransmission());
    }

    @Test
    void testDeleteTractor() throws Exception {
        // Create and save a test tractor
        Tractor tractor = new Tractor()
                .setManufacturer(ManufacturersEnum.JohnDeere)
                .setModel("Model1")
                .setYear(2020)
                .setDescription("Test description")
                .setWorkHours(100)
                .setImageUrl("http://image.com")
                .setEngine(EngineTypeEnum.Diesel)
                .setTransmission(TransmissionTypeEnum.CVT);

        tractor = tractorRepository.save(tractor);

        mockMvc.perform(delete("/tractors/" + tractor.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertFalse(tractorRepository.findById(tractor.getId()).isPresent());
    }
}
