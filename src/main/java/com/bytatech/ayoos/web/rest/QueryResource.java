package com.bytatech.ayoos.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.time.LocalDate;
import java.util.*;
import org.springframework.data.geo.Distance;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bytatech.ayoos.client.doctor.api.ReservedSlotResourceApi;
import com.bytatech.ayoos.client.doctor.model.*;
import com.bytatech.ayoos.client.domain.*;
import com.bytatech.ayoos.service.*;

//import com.bytatech.jest.example.jestDemo.search.custom.ArtistSearchRepositoryCustomImpl;
import com.github.vanroy.springdata.jest.aggregation.AggregatedPage;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class QueryResource {
	@Autowired
	ReservedSlotResourceApi reservedSlotResourceApi;
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

	@GetMapping("/findByLocationWithin")
	public ResponseEntity<List<Doctor>> searchByLocation(@RequestParam("location") Double[] location,
			@RequestParam("distance") Distance distance, Pageable pageable) {
		Point point = new Point(location[0], location[1]);
		return ResponseEntity.ok().body(queryService.findByLocationWithin(point, distance, pageable).getContent());
	}

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

	@GetMapping("/slot/{date}/{doctorId}")
	public ResponseEntity<List<ReservedSlotDTO>> createSlot(@PathVariable LocalDate date, @PathVariable Long doctorId,
			@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size,
			@RequestParam(value = "sort") ArrayList<String> sort) {
		return reservedSlotResourceApi.createSlotUsingPOST(date, doctorId, page, size, sort);
	}

}
