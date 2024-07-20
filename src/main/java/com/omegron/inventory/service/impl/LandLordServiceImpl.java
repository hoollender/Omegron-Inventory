package com.omegron.inventory.service.impl;

import com.omegron.inventory.model.dto.*;
import com.omegron.inventory.model.entity.LandLord;
import com.omegron.inventory.repository.LandLordRepository;
import com.omegron.inventory.service.LandLordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandLordServiceImpl implements LandLordService {
    private final LandLordRepository landLordRepository;

    public LandLordServiceImpl(LandLordRepository landLordRepository) {
        this.landLordRepository = landLordRepository;
    }

    @Override
    public long addLandLord(AddLandLordDTO addLandLordDTO) {
        return landLordRepository.save(map(addLandLordDTO)).getId();
    }

    @Override
    public void updateLandLord(Long id, LandLordDTO landLordDTO) {
        LandLord landLord = landLordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid LandLord Id:" + id));
        landLord.setFirstName(landLordDTO.firstName());
        landLord.setMiddleName(landLordDTO.middleName());
        landLord.setLastName(landLordDTO.lastName());
        landLord.setDateOfBirth(landLordDTO.dateOfBirth());
        landLord.setAddress(landLordDTO.address());
        landLord.setPersonalNumber(landLordDTO.personalNumber());
        landLord.setPersonalID(landLordDTO.personalID());
        landLord.setValidityID(landLordDTO.validityID());
        landLord.setDateOfIssue(landLordDTO.dateOfIssue());
        landLordRepository.save(landLord);
    }

    @Override
    public void deleteLandLord(long landLordId) {
        landLordRepository.deleteById(landLordId);
    }

    @Override
    public LandLordDTO getLandLordDetails(Long id) {
        return this.landLordRepository.findById(id)
                .map(LandLordServiceImpl::toLandLordDetails)
                .orElseThrow();
    }

    @Override
    public List<LandLordDTO> getAllLandLordsSummary() {
        return landLordRepository
                .findAll()
                .stream()
                .map(LandLordServiceImpl::toLandLordSummary)
                .toList();
    }

    @Override
    public LandLordDTO findById(Long id) {
        LandLord landLord = landLordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid LandLord Id:" + id));
        return mapToLandLordDTO(landLord);
    }

    //MAPPING
    private static LandLordDTO toLandLordSummary(LandLord landLord) {
        return new LandLordDTO(
                landLord.getId(),
                landLord.getFirstName(),
                landLord.getMiddleName(),
                landLord.getLastName(),
                landLord.getDateOfBirth(),
                landLord.getAddress(),
                landLord.getPersonalNumber(),
                landLord.getPersonalID(),
                landLord.getValidityID(),
                landLord.getDateOfIssue());
    }

    private static LandLordDTO toLandLordDetails(LandLord landLord) {
        return new LandLordDTO(landLord.getId(),
                landLord.getFirstName(),
                landLord.getMiddleName(),
                landLord.getLastName(),
                landLord.getDateOfBirth(),
                landLord.getAddress(),
                landLord.getPersonalNumber(),
                landLord.getPersonalID(),
                landLord.getValidityID(),
                landLord.getDateOfIssue());
    }

    private LandLordDTO mapToLandLordDTO(LandLord landLord) {
        return new LandLordDTO(landLord.getId(),
                landLord.getFirstName(),
                landLord.getMiddleName(),
                landLord.getLastName(),
                landLord.getDateOfBirth(),
                landLord.getAddress(),
                landLord.getPersonalNumber(),
                landLord.getPersonalID(),
                landLord.getValidityID(),
                landLord.getDateOfIssue());
    }

    private static LandLord map(AddLandLordDTO addLandLordDTO) {
        return new LandLord()
                .setFirstName(addLandLordDTO.firstName())
                .setMiddleName(addLandLordDTO.middleName())
                .setLastName(addLandLordDTO.lastName())
                .setDateOfBirth(addLandLordDTO.dateOfBirth())
                .setAddress(addLandLordDTO.address())
                .setPersonalNumber(addLandLordDTO.personalNumber())
                .setPersonalID(addLandLordDTO.personalID())
                .setValidityID(addLandLordDTO.validityID())
                .setDateOfIssue(addLandLordDTO.dateOfIssue());
    }
}
