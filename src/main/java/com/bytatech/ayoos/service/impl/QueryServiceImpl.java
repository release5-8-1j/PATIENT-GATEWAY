package com.bytatech.ayoos.service.impl;

import java.util.*;
import static org.elasticsearch.index.query.QueryBuilders.*;

import static org.elasticsearch.action.search.SearchType.QUERY_THEN_FETCH;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/*import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytatech.ayoos.client.appointment.model.Appointment;
import com.bytatech.ayoos.client.doctor.model.*;

import com.bytatech.ayoos.client.patient.model.*;
import com.bytatech.ayoos.service.QueryService;


import com.google.gson.JsonObject;



import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

/**
 * Query Service Implementation
 */
@Service
@Transactional
public class QueryServiceImpl implements QueryService {

	


	/*@Override
	public Page<Doctor> findAllDoctors(Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
		List<Doctor> d = elasticsearchOperations.queryForList(searchQuery, Doctor.class);

		for (Doctor d1 : d) {
			System.out.println("################################" + d1.getPracticeSince());
		}

		return elasticsearchOperations.queryForPage(searchQuery, Doctor.class);

	}

	
	@Override
	public List<TestDate> findAllTestDates(Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
		return elasticsearchOperations.queryForList(searchQuery,TestDate.class);

	}
	
	@Override
	public Optional<Doctor> findDoctorByDoctorId(String doctorId) {

		StringQuery stringQuery = new StringQuery(termQuery("doctorId", doctorId).toString());
		return Optional.of(elasticsearchOperations.queryForObject(stringQuery, Doctor.class));
	}
	
	@Override
	public Optional<Appointment> findAppointmentByTrackingId(String trackingId){
		
		StringQuery stringQuery=new StringQuery(termQuery("trackingId.keyword", trackingId).toString());
		return Optional.of(elasticsearchOperations.queryForObject(stringQuery, Appointment.class));
	}

	@Override
	public List<String> findAllQualifications(Pageable pageable) {
		List<String> qualificationList = new ArrayList<String>();
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery())
				.withSearchType(QUERY_THEN_FETCH).withIndices("qualification").withTypes("qualification")
				.addAggregation(AggregationBuilders.terms("distinct_qualification").field("qualification.keyword"))
				.build();

		AggregatedPage<Qualification> result = elasticsearchTemplate.queryForPage(searchQuery, Qualification.class);
		TermsAggregation subjectAgg = result.getAggregation("distinct_qualification", TermsAggregation.class);
		
		List<Entry> bucket = subjectAgg.getBuckets();

		for (int i = 0; i < subjectAgg.getBuckets().size(); i++) {
			qualificationList.add(subjectAgg.getBuckets().get(i).getKey());
		}
		

		return qualificationList;

	}

	@Override
	public Page<Doctor> facetSearch(String specialization, Double ratings, Double feeFrom, Double feeTo,
			Pageable pageable) {

		List<QueryBuilders> queryList = new ArrayList<QueryBuilders>();
		// QueryBuilders.matchQuery("specialization", specialization);
		// queryList.add( QueryBuilders.matchQuery("totalRatings", ratings));
		// queryList.add( QueryBuilders.rangeQuery("fees").gte("feeFrom").lte("feeTo"));
		// queryList.add( QueryBuilders.matchQuery("workplace.name", workplaceName));

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery())

				.withFilter(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("specialization", specialization))
						.should(QueryBuilders.matchQuery("totalRatings", ratings))
						.should(QueryBuilders.rangeQuery("paymentSettings.amount").gte("fr").lte("to"))
				 .should(QueryBuilders.matchQuery("workplace.name", workplaceName)) ).build();
		return elasticsearchOperations.queryForPage(searchQuery, Doctor.class);
	}

	
	
	
	@Override
	public Page<Review> findReviewByDoctorId(String doctorId, Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("doctor.doctorId", doctorId))
				.build();

		return elasticsearchOperations.queryForPage(searchQuery, Review.class);

	}

	@Override
	public Page<Doctor> findDoctors(String searchTerm, Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("doctorId", searchTerm))
						.should(QueryBuilders.matchQuery("specialization", searchTerm)))
				.build();

		return elasticsearchOperations.queryForPage(searchQuery, Doctor.class);
	}

	@Override
	public Optional<Patient> findPatient(String patientCode) {
		StringQuery stringQuery = new StringQuery(matchQuery("patientCode", patientCode).toString());
		return Optional.of(elasticsearchOperations.queryForObject(stringQuery, Patient.class));
	}

	@Override
	public List<WorkPlace> findByLocationWithin(Point point, Distance distance) {

		return elasticsearchTemplate.queryForList(getGeoQuery(point, distance), WorkPlace.class);

	}

	private CriteriaQuery getGeoQuery(Point point, Distance distance) {
		return new CriteriaQuery(new Criteria("location").within(point, distance));
	}


	 (non-Javadoc)
	 * @see com.bytatech.ayoos.service.QueryService#findRatingByDoctorIdAndPatientName(java.lang.String, java.lang.String)
	 
	@Override
	public UserRating findRatingByDoctorIdAndPatientName(String doctorId, String patientCode) {

		StringQuery stringQuery = new StringQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("doctor.doctorId", doctorId))
				.must(QueryBuilders.termQuery("userName", patientCode)).toString());
			
		return elasticsearchOperations.queryForObject(stringQuery, UserRating.class);
	}


	 (non-Javadoc)
	 * @see com.bytatech.ayoos.service.QueryService#findReviewByDoctorIdAndPatientName(java.lang.String, java.lang.String)
	 
	@Override
	public Review findReviewByDoctorIdAndPatientName(String doctorId, String patientCode) {
		StringQuery stringQuery = new StringQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("doctor.doctorId", doctorId))
				.must(QueryBuilders.termQuery("userName", patientCode)).toString());
			
		return elasticsearchOperations.queryForObject(stringQuery, Review.class);
	}

	
	@Override
	public Page<Patient> findAllPatientWithoutSearch(Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
		return elasticsearchOperations.queryForPage(searchQuery, Patient.class);
	}*/
}
