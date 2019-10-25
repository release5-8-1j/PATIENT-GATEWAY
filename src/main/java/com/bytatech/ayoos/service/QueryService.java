package com.bytatech.ayoos.service;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.bytatech.ayoos.client.appointment.model.Appointment;
import com.bytatech.ayoos.client.doctor.model.*;

import com.bytatech.ayoos.client.patient.model.*;


public interface QueryService {

	// search using QueryDsl(MATCHALL QUERY)
	/*public Page<Doctor> findAllDoctors(Pageable pageable);
	public Optional<Appointment> findAppointmentByTrackingId(String trackingId);

	public List<String> findAllQualifications(Pageable pageable);

	public Page<Doctor> facetSearch(String specialization,Double ratings, Double feeFrom,
			Double feeTo, Pageable pageable);

	public List<WorkPlace>  findByLocationWithin(Point point, Distance distance);


	public Page<Review> findReviewByDoctorId(String doctorId, Pageable pageable);


	*//**
	 * @param searchTerm
	 * @return
	 *//*
	public Page<Doctor> findDoctors(String searchTerm,Pageable pageable);

	public Optional<Patient> findPatient(String patientCode);

	public  Optional<Doctor> findDoctorByDoctorId(String doctorId);

	List<TestDate> findAllTestDates(Pageable pageable);

	*//**
	 * @param doctorId
	 * @param userName
	 * @return
	 *//*
	public UserRating findRatingByDoctorIdAndPatientName(String doctorId, String patientCode);

	*//**
	 * @param doctorIds
	 * @param userName
	 * @return
	 *//*
	public Review findReviewByDoctorIdAndPatientName(String doctorId, String patientCode);

	*//**
	 * @param pageable
	 * @return
	 *//*
	Page<Patient> findAllPatientWithoutSearch(Pageable pageable);


	
	
	
	
	*/
	
	
	

}
