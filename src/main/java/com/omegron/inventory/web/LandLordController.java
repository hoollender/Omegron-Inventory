package com.omegron.inventory.web;

import com.omegron.inventory.model.dto.*;
import com.omegron.inventory.service.LandLordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/landlords")
public class LandLordController {
    private final LandLordService landLordService;

    public LandLordController(LandLordService landLordService) {
        this.landLordService = landLordService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<LandLordDTO>> getAllLandLords() {
        return ResponseEntity.ok(landLordService.getAllLandLordsSummary());

    }

    @GetMapping("/details/{id}")
    public ResponseEntity<LandLordDTO> getLandLordDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(landLordService.getLandLordDetails(id));
    }

    @PostMapping("/add")
    public ResponseEntity<LandLordDTO> addLandLord(@RequestBody AddLandLordDTO addLandLordDTO) {
        landLordService.addLandLord(addLandLordDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateLandlord(@PathVariable Long id, @RequestBody LandLordDTO landLordDTO) {
        landLordService.updateLandLord(id, landLordDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LandLordDTO> deleteLandLord(@PathVariable("id") Long id) {
        landLordService.deleteLandLord(id);
        return ResponseEntity.noContent().build();
    }
}
