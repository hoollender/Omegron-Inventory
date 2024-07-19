package com.omegron.inventory.model.dto;

import com.omegron.inventory.model.enums.EngineTypeEnum;
import com.omegron.inventory.model.enums.ManufacturersEnum;
import com.omegron.inventory.model.enums.TransmissionTypeEnum;

public record TractorSummaryDTO(
        Long id,
        ManufacturersEnum manufacturer,
        String model,
        Integer year,
        String description,
        Integer workHours,
        String imageUrl,
        EngineTypeEnum engineType,
        TransmissionTypeEnum transmissionType
) {
}
