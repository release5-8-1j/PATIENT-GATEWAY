package com.bytatech.ayoos.web.rest;
import com.bytatech.ayoos.client.appointment.api.*;
import com.bytatech.ayoos.client.appointment.model.AdditionalInformationRequest;
import com.bytatech.ayoos.client.appointment.model.AppointmentRequest;
import com.bytatech.ayoos.client.appointment.model.ConsultationDetails;
import com.bytatech.ayoos.client.appointment.model.PaymentConfirmationRequest;
import com.bytatech.ayoos.client.appointment.model.ProcessPayment;
import com.bytatech.ayoos.client.doctor.model.*;

import com.bytatech.ayoos.client.patient.api.CommandResourceApi;
import com.bytatech.ayoos.client.patient.api.PatientResourceApi;
import com.bytatech.ayoos.client.patient.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	AppointmentCommandResourceApi appointmentCommandResourceApi;
	
	
	@Autowired
	CommandResourceApi patientcommandResourceApi;
	
	@Autowired
	UserRatingResourceApi userRatingResourceApi;
	
	@Autowired
  ReviewResourceApi reviewResourceApi;
	
	@Autowired
	PatientResourceApi patientResourceApi;
	
	
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
		return  patientcommandResourceApi.createPatientUsingPOST(patientDTO);
	}

	@PutMapping("/patients")
	public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
		return  patientcommandResourceApi.updatePatientUsingPUT(patientDTO);
	}
	@PostMapping("/address-lines")
	public ResponseEntity<AddressLineDTO> createAddressLine(@RequestBody AddressLineDTO addressLineDTO) {
		return  patientcommandResourceApi.createAddressLineUsingPOST1(addressLineDTO);
	}
	
	@PutMapping("/address-lines")
	public ResponseEntity<AddressLineDTO> updateAddressLine(@RequestBody AddressLineDTO addressLineDTO) {
		return  patientcommandResourceApi.updateAddressLineUsingPUT1(addressLineDTO);
	}
	

	
	@PostMapping("/appointments/additionalInformationRequest/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createAdditionalInformationRequest(@PathVariable("taskId") String taskId,@RequestBody AdditionalInformationRequest additionalInformationRequest) {
		return appointmentCommandResourceApi.additionalInformationRequestUsingPOST(taskId, additionalInformationRequest);
	}
	
	@PostMapping("/appointments/collectAdditionalDetails/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createCollectAdditionalDetails(@PathVariable("taskId") String taskId,@RequestBody ConsultationDetails consultationDetails) {
		return appointmentCommandResourceApi.collectAdditionalDetailsUsingPOST(taskId, consultationDetails);
	}
	
	@PostMapping("/appointments/confirmPayment/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createConfirmPayment(@PathVariable("taskId") String taskId,@RequestBody PaymentConfirmationRequest paymentConfirmationRequest) {
		return appointmentCommandResourceApi.confirmPaymentUsingPOST(taskId, paymentConfirmationRequest);
	}
	
	@PostMapping("/appointments/confirmRegistration/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createConfirmRegistration(@PathVariable("taskId") String taskId) {
		return appointmentCommandResourceApi.confirmRegistrationUsingPOST(taskId);
	}
	
	@PostMapping("/appointments/initiateAppointment")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createInitiateAppointment(@RequestBody AppointmentRequest appointmentRequest) {
		ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> c= appointmentCommandResourceApi.initiateAppointmentUsingPOST(appointmentRequest);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>"+c.getBody());
		return c;
	}
	
	@PostMapping("/appointments/processPayment/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createProcessPayment(@PathVariable("taskId") String taskId,@RequestBody ProcessPayment processPayment) {
		return appointmentCommandResourceApi.processPaymentUsingPOST(taskId, processPayment);
	}
	
	
/*	@PostMapping("/appointments/processPayment/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createProcessPayment(@PathVariable("taskId") String taskId,@RequestBody ProcessPayment processPayment) {
		return appointmentCommandResourceApi.processPaymentUsingPOST(taskId, processPayment);
	}*/
	
	
	
	
	
	
	@PostMapping("/patients/modelToDto")
	public ResponseEntity<PatientDTO> modelToDto(@RequestBody Patient patient){
		return patientResourceApi.modelToDtoUsingPOST(patient);
		
	}
	

}
