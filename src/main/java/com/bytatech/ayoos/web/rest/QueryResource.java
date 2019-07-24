package com.bytatech.ayoos.web.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.bytatech.ayoos.client.doctor.api.ReviewResourceApi;
import com.bytatech.ayoos.client.doctor.api.UserRatingResourceApi;
import com.bytatech.ayoos.client.doctor.model.*;
import com.bytatech.ayoos.client.doctor.model.ReservedSlotDTO;
import com.bytatech.ayoos.client.doctor.model.Review;
import com.bytatech.ayoos.client.doctor.model.WorkPlace;
import com.bytatech.ayoos.client.domain.TestDate;
import com.bytatech.ayoos.client.medicalnews.api.GoogleMedicalNewsApi;
import com.bytatech.ayoos.client.medicalnews.model.GoogleMedicalNews;
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

	private final Logger log = LoggerFactory.getLogger(QueryResource.class);

	@Autowired
	ReservedSlotResourceApi reservedSlotResourceApi;

	@Autowired
	AddressLineResourceApi addressLineResourceApi;

	@Autowired
	PatientResourceApi patientResourceApi;

	@Autowired
	QueryService queryService;

	@Autowired
	UserRatingResourceApi userRatingResourceApi;

	@Autowired
	ReviewResourceApi reviewResourceApi;

	@Autowired
	GoogleMedicalNewsApi googleApi;

	@GetMapping("/findAllDoctors")
	public ResponseEntity<List<Doctor>> findAllDoctors(Pageable pageable) {
		return ResponseEntity.ok().body(queryService.findAllDoctors(pageable).getContent());
	}

	@GetMapping("/findAllDates")
	public List<TestDate> findAllDates(Pageable pageable) {
		return queryService.findAllTestDates(pageable);
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

	/*
	 * @GetMapping("/findByLocationWithin") public ResponseEntity<List<Doctor>>
	 * searchByLocation(@RequestParam("location") Double[] location,
	 * 
	 * @RequestParam("distance") Double distance, Pageable pageable) { Point
	 * point = new Point(location[0], location[1]); return
	 * ResponseEntity.ok().body(queryService.findByLocationWithin(point, new
	 * Distance(distance,Metrics.KILOMETERS), pageable).getContent()); }
	 */

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
	public ResponseEntity<List<ReservedSlotDTO>> test2(@PathVariable String date, @PathVariable Long doctorId) {

		return reservedSlotResourceApi.test2UsingGET(date, doctorId);
	}

	/*
	 * @GetMapping(
	 * "/location/findByLocationWithin""/location/findByLocationWithin/{lat}/{lon}/{distance}")
	 * public ResponseEntity<List<Doctor>> searchByLocationWithin(@PathVariable
	 * Double lat,
	 * 
	 * @PathVariable Double lon,@PathVariable Double distance, Pageable
	 * pageable) { Point point = new Point(10.789428,76.573091); return
	 * ResponseEntity.ok().body(queryService.findByLocationWithin(point,new
	 * Distance(50.00, Metrics.KILOMETERS) new
	 * Distance(distance,Metrics.KILOMETERS), pageable).getContent()); }
	 */

	@GetMapping("/findRatingReview/{doctorId}")
	public ResponseEntity<List<RatingReview>> findRatingReviewByStoreidAndCustomerName(@PathVariable String doctorId,
			/* @PathVariable String name */Pageable pageable) {
		List<RatingReview> listOfRatingreview = new ArrayList<RatingReview>();

		List<Patient> patientList = queryService.findAllPatientWithoutSearch(pageable).getContent();

		for (Patient p : patientList) {

			log.info(">>>>>>>>>>>>>>>>>>> patient:   " + p + "   >>>>>>>>>>>>>>>>");

			UserRating rating = queryService.findRatingByDoctorIdAndPatientName(doctorId, p.getPatientCode());

			log.info(">>>>>>>>>>>>>>>>>>> rating:  " + rating + "   >>>>>>>>>>>>>>>>");

			Review review = queryService.findReviewByDoctorIdAndPatientName(doctorId, p.getPatientCode());

			log.info(">>>>>>>>>>>>>>>>>>> review:  " + review + "   >>>>>>>>>>>>>>>>");

			if (rating != null) {

				RatingReview ratingReview = new RatingReview();

				ratingReview.setRating(userRatingResourceApi.modelToDtoUsingPOST1(rating).getBody());

				if (review != null) {

					ratingReview.setReview(reviewResourceApi.modelToDtoUsingPOST(review).getBody());

				}

				log.info(">>>>>>>>>>>>>>>>>>> ratingReview:  " + ratingReview + "   >>>>>>>>>>>>>>>>");

				listOfRatingreview.add(ratingReview);

				log.info(">>>>>>>>>>>>>>>>>>> listOfRatingreview:  " + listOfRatingreview + "   >>>>>>>>>>>>>>>>");
			}
		}

		return ResponseEntity.ok().body(listOfRatingreview);

	}

	@GetMapping("/address-linesByPatientId/{patientId}")
	public ResponseEntity<List<AddressLineDTO>> getAllAddressLinesByPatientId(
			@PathVariable("patientId") Long patientId) {
		return addressLineResourceApi.getAllAddressLinesByPatientIdUsingGET(patientId);

	}

	@GetMapping("/Dr-slots/{date}/{doctorId}")
	public ResponseEntity<List<ReservedSlotDTO>> findSlots(@PathVariable String date, @PathVariable Long doctorId) {
		return reservedSlotResourceApi.test2UsingGET(date, doctorId);
	}

	/*
	 * @GetMapping(
	 * "/location/findByLocationWithin""/location/findByLocationWithin/{lat}/{lon}/{distance}")
	 * public List<WorkPlace> searchByLocationWithin(@PathVariable Double lat,
	 * 
	 * @PathVariable Double lon,@PathVariable Double distance, Pageable
	 * pageable) { Point point = new Point(10.789428,76.573091); return
	 * queryService.findByLocationWithin(point,new Distance(50.00,
	 * Metrics.KILOMETERS) new Distance(distance,Metrics.KILOMETERS), pageable);
	 * }
	 * 
	 */

	@GetMapping("/location/findByNearestLocation/{latLon}/{kiloMeter}")
	public List<WorkPlace> searchByNearestLocation(@PathVariable String latLon, @PathVariable Double kiloMeter) {

		String[] latLons = latLon.split(",");

		double lat = Double.parseDouble(latLons[0]);

		double lon = Double.parseDouble(latLons[1]);

		return queryService.findByLocationWithin(new Point(lat, lon), new Distance(kiloMeter, Metrics.KILOMETERS));
	}

	@GetMapping("/googleMedicalNews")
	public ResponseEntity<GoogleMedicalNews> getMedicalNews() {

		String sources = "medical-news-today";
		String apiKey = "6ada41f2bee1449bb7323c480a6d6986";
		return googleApi.getMedicalNews(sources, apiKey);
	}
}
