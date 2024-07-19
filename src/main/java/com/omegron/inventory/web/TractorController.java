package com.omegron.inventory.web;

import com.omegron.inventory.model.dto.AddTractorDTO;
import com.omegron.inventory.model.dto.TractorDTO;
import com.omegron.inventory.model.dto.TractorDetailsDTO;
import com.omegron.inventory.model.dto.TractorSummaryDTO;
import com.omegron.inventory.service.TractorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tractors")
public class TractorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TractorController.class);

    private final TractorService tractorService;

    public TractorController(TractorService tractorService) {
        this.tractorService = tractorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TractorSummaryDTO>> getAllTractors() {
        return ResponseEntity.ok(tractorService.getAllTractorsSummary());

    }

    @GetMapping("/details/{id}")
    public ResponseEntity<TractorDetailsDTO> getTractorDetails(@PathVariable("id") Long id) {
        LOGGER.info("Going to get a tractor {}", id);
        return ResponseEntity.ok(tractorService.getTractorDetails(id));
    }

    @PostMapping("/add")
    public ResponseEntity<TractorDTO> addTractor(@RequestBody AddTractorDTO addTractorDTO) {
        LOGGER.info("Going to add a tractor {}", addTractorDTO);
        tractorService.addTractor(addTractorDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateTractor(@PathVariable Long id, @RequestBody TractorDetailsDTO tractorDetailsDTO) {
        tractorService.updateTractor(id, tractorDetailsDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TractorDTO> deleteTractor(@PathVariable("id") Long id) {
        LOGGER.info("Going to delete a tractor {}", id);
        tractorService.deleteTractor(id);
        return ResponseEntity.noContent().build();
    }
}
