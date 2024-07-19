package com.omegron.inventory.service;

import com.omegron.inventory.model.dto.AddTractorDTO;
import com.omegron.inventory.model.dto.TractorDetailsDTO;
import com.omegron.inventory.model.dto.TractorSummaryDTO;

import java.util.List;

public interface TractorService {

    long addTractor(AddTractorDTO addTractorDTO);

    void updateTractor(Long id, TractorDetailsDTO tractorDetailsDTO);

    void deleteTractor(long tractorId);

    TractorDetailsDTO getTractorDetails(Long id);

    List<TractorSummaryDTO> getAllTractorsSummary();

    TractorDetailsDTO findById(Long id);
}
