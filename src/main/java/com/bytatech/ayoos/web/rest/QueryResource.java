package com.bytatech.ayoos.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bytatech.ayoos.client.doctor.api.ReservedSlotResourceApi;
import com.bytatech.ayoos.client.doctor.model.*;
import com.bytatech.ayoos.client.doctor.model.ReservedSlotDTO;
import com.bytatech.ayoos.client.doctor.model.Review;
import com.bytatech.ayoos.client.doctor.model.WorkPlace;
import com.bytatech.ayoos.client.patient.model.*;
import com.bytatech.ayoos.client.patient.api.AddressLineResourceApi;
import com.bytatech.ayoos.client.patient.api.PatientResourceApi;
import com.bytatech.ayoos.client.patient.model.AddressLineDTO;
import com.bytatech.ayoos.client.patient.model.PatientDTO;
import com.bytatech.ayoos.service.QueryService;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class QueryResource {
	@Autowired
	ReservedSlotResourceApi reservedSlotResourceApi;
	
	@Autowired
	AddressLineResourceApi addressLineResourceApi;
	
	@Autowired
	PatientResourceApi patientResourceApi;
	
	@Autowired
	QueryService queryService;

	@GetMapping("/findAllDoctors")
	public ResponseEntity<List<Doctor>> findAllDoctors(Pageable pageable) {
		return ResponseEntity.ok().body(queryService.findAllDoctors(pageable).getContent());
	}

	@GetMapping("/findAllQualification")
	public List<String> findAllQualification(Pageable pageable) {
		return queryService.findAllQualifications(pageable);
	}

	@GetMapping("/facetSearch/{specialization}/{rating}/{feeFrom}/{feeTo}")
	public ResponseEntity<List<Doctor>> facetSearch(@PathVariable String specialization, @PathVariable Double rating,
			@PathVariable Double feeFrom, @PathVariable Double feeTo, Pageable pageable) {
		return ResponseEntity.ok()
				.body(queryService.facetSearch(specialization, rating, feeFrom, feeTo, pageable).getContent());

	}

	/*@GetMapping("/findByLocationWithin")
	public ResponseEntity<List<Doctor>> searchByLocation(@RequestParam("location") Double[] location,
			@RequestParam("distance") Double distance, Pageable pageable) {
		Point point = new Point(location[0], location[1]);
		return ResponseEntity.ok().body(queryService.findByLocationWithin(point, new Distance(distance,Metrics.KILOMETERS), pageable).getContent());
	}*/

	@GetMapping("/findReviewByDoctorId/{doctorId}")
	public ResponseEntity<List<Review>> findReviewByDoctorId(@PathVariable String doctorId, Pageable pageable) {
		return ResponseEntity.ok().body(queryService.findReviewByDoctorId(doctorId, pageable).getContent());

	}

	@GetMapping("/doctor/{searchTerm}")
	public ResponseEntity<List<Doctor>> findDoctors(@PathVariable String searchTerm, Pageable pageable) {

		return ResponseEntity.ok().body(queryService.findDoctors(searchTerm, pageable).getContent());
	}

	@GetMapping("/patient/{patientCode}")
	public ResponseEntity<Patient> findPatient(@PathVariable String patientCode) {
		Optional<Patient> patient = queryService.findPatient(patientCode);
		return ResponseUtil.wrapOrNotFound(patient);
	}
	

	@GetMapping("/doctors/{doctorId}")
	public ResponseEntity<Doctor> findDoctorByDoctorId(@PathVariable String doctorId) {
		Optional<Doctor> doctor = queryService.findDoctorByDoctorId(doctorId);
		return ResponseUtil.wrapOrNotFound(doctor);
	}
	
	
	@GetMapping("/test2/{date}/{doctorId}")
	public ResponseEntity<List<ReservedSlotDTO>> test2(@PathVariable  String date, @PathVariable Long doctorId) {
		
		
		return reservedSlotResourceApi.test2UsingGET(date, doctorId);
	}
	
	
	@GetMapping("/location/findByLocationWithin/{lat}/{lon}/{distance}")
	public ResponseEntity<List<WorkPlace>> searchByLocationWithin(@PathVariable Double lat,
			@PathVariable Double lon,@PathVariable Double distance, Pageable pageable) {
		Point point = new Point(lat, lon);
		return ResponseEntity.ok().body(queryService.findByLocationWithin(point, new Distance(distance,Metrics.KILOMETERS), pageable).getContent());
	}
	
	
	
	
	
	@GetMapping("/address-linesByPatientId/{patientId}")
	public ResponseEntity<List<AddressLineDTO>> getAllAddressLinesByPatientId(@PathVariable("patientId") Long patientId) {
		return addressLineResourceApi.getAllAddressLinesByPatientIdUsingGET(patientId);

	}
	
	
}
