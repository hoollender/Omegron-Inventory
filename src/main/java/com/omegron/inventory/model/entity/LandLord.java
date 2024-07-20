package com.omegron.inventory.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "landlords")
public class LandLord extends BaseEntity{

    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Middle name is required.")
    @Size(min = 2, max = 50, message = "Middle name must be between 2 and 50 characters.")
    @Column(name = "middle_name", nullable = false, length = 50)
    private String middleName;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotNull(message = "Please insert date of birth.")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Please insert an address.")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "Please insert a personal number.")
    @Column(name = "personal_number", nullable = false)
    private String personalNumber;

    @NotBlank(message = "Please insert a personal ID number.")
    @Column(name = "personal_id", nullable = false)
    private String personalID;

    @NotNull(message = "Please insert a validity of the ID.")
    @Column(name = "validity_id", nullable = false)
    private LocalDate validityID;

    @NotNull(message = "Please insert the date of issue.")
    @Column(name = "date_of_issue", nullable = false)
    private LocalDate dateOfIssue;

    //GETTERS AND SETTERS


    public String getFirstName() {
        return firstName;
    }

    public LandLord setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public LandLord setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public LandLord setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LandLord setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LandLord setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public LandLord setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
        return this;
    }

    public String getPersonalID() {
        return personalID;
    }

    public LandLord setPersonalID(String personalID) {
        this.personalID = personalID;
        return this;
    }

    public LocalDate getValidityID() {
        return validityID;
    }

    public LandLord setValidityID(LocalDate validityID) {
        this.validityID = validityID;
        return this;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public LandLord setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
        return this;
    }
}
