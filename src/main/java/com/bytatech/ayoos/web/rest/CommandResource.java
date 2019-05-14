package com.bytatech.ayoos.web.rest;
import com.bytatech.ayoos.client.doctor.model.*;
import com.bytatech.ayoos.client.patient.api.CommandResourceApi;
import com.bytatech.ayoos.client.patient.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bytatech.ayoos.client.doctor.api.*;
@RestController
@RequestMapping("/api")
public class CommandResource {
	
	@Autowired
	CommandResourceApi commandResourceApi;
	
	@Autowired
	UserRatingResourceApi userRatingResourceApi;
	
	@Autowired
  ReviewResourceApi reviewResourceApi;
	
	
	@PostMapping("/rating")
    public ResponseEntity<UserRatingDTO> ratedoctor(@RequestBody UserRatingDTO userRatingDTO) {
    
    	return userRatingResourceApi.createUserRatingUsingPOST(userRatingDTO);
    }

	@PostMapping("/review")
    public ResponseEntity<ReviewDTO> reviewdoctor(@RequestBody ReviewDTO reviewDTO) {
    
		return reviewResourceApi.createReviewUsingPOST(reviewDTO);
    }
	
	@PostMapping("/patients")
	public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
		return commandResourceApi.createPatientUsingPOST(patientDTO);
	}

	@PutMapping("/patients")
	public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
		return commandResourceApi.updatePatientUsingPUT(patientDTO);
	}
	@PostMapping("/address-lines")
	public ResponseEntity<AddressLineDTO> createAddressLine(@RequestBody AddressLineDTO addressLineDTO) {
		return commandResourceApi.createAddressLineUsingPOST1(addressLineDTO);
	}
	
	@PutMapping("/address-lines")
	public ResponseEntity<AddressLineDTO> updateAddressLine(@RequestBody AddressLineDTO addressLineDTO) {
		return commandResourceApi.updateAddressLineUsingPUT1(addressLineDTO);
	}
	
	
	
	
	
	
	

}
