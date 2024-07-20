package com.omegron.inventory.service;

import com.omegron.inventory.model.dto.AddLandLordDTO;
import com.omegron.inventory.model.dto.LandLordDTO;


import java.util.List;

public interface LandLordService {
    long addLandLord(AddLandLordDTO addLandLordDTO);

    void updateLandLord(Long id, LandLordDTO landLordDTO);

    void deleteLandLord(long landLordId);

    LandLordDTO getLandLordDetails(Long id);

    List<LandLordDTO> getAllLandLordsSummary();

    LandLordDTO findById(Long id);
}
