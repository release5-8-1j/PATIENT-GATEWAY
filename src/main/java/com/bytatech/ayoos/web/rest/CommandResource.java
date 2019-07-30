package com.bytatech.ayoos.web.rest;

import com.bytatech.ayoos.client.appointment.api.*;
import com.bytatech.ayoos.client.appointment.model.AdditionalInformationRequest;
import com.bytatech.ayoos.client.appointment.model.Appointment;
import com.bytatech.ayoos.client.appointment.model.AppointmentConfirmationRequest;
import com.bytatech.ayoos.client.appointment.model.AppointmentDTO;
import com.bytatech.ayoos.client.appointment.model.AppointmentRequest;
import com.bytatech.ayoos.client.appointment.model.ConsultationDetails;
import com.bytatech.ayoos.client.appointment.model.ConsultationInfoDTO;
import com.bytatech.ayoos.client.appointment.model.PaymentConfirmationRequest;
import com.bytatech.ayoos.client.appointment.model.ProcessPayment;
import com.bytatech.ayoos.client.appointment.model.SymptomDTO;
import com.bytatech.ayoos.client.appointment.model.Syndrome;
import com.bytatech.ayoos.client.doctor.model.*;

import com.bytatech.ayoos.client.patient.api.CommandResourceApi;
import com.bytatech.ayoos.client.patient.api.PatientResourceApi;
import com.bytatech.ayoos.client.patient.model.*;
import com.bytatech.ayoos.client.payment.api.RazorpayCommandResourceApi;
import com.bytatech.ayoos.client.payment.model.OrderRequest;
import com.bytatech.ayoos.client.payment.model.OrderResponse;
import com.bytatech.ayoos.client.domain.*;
import com.bytatech.ayoos.repository.search.DoctorSearchRepository;
import com.bytatech.ayoos.service.QueryService;
import com.bytatech.ayoos.repository.search.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	private final Logger log = LoggerFactory.getLogger(CommandResource.class);

	@Autowired
	QueryService queryService;

	@Autowired
	DoctorResourceApi doctorResourceApi;

	@Autowired
	AppointmentCommandResourceApi appointmentCommandResourceApi;

	@Autowired
	RazorpayCommandResourceApi razorpayCommandResource;

	@Autowired
	CommandResourceApi patientcommandResourceApi;

	@Autowired
	SymptomResourceApi symptomResourceApi;

	@Autowired
	UserRatingResourceApi userRatingResourceApi;

	@Autowired
	ConsultationInfoResourceApi consultationApi;

	@Autowired
	ReviewResourceApi reviewResourceApi;

	@Autowired
	PatientResourceApi patientResourceApi;

	@Autowired
	DoctorSearchRepository doctorSearchRepository;

	@Autowired
	TestDateSearchRepository testDateSearchRepository;
	private Pageable pageRequest = new PageRequest(0, 10);

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
		return patientcommandResourceApi.createPatientUsingPOST(patientDTO);
	}

	@PutMapping("/patients")
	public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
		return patientcommandResourceApi.updatePatientUsingPUT(patientDTO);
	}

	@PostMapping("/address-lines")
	public ResponseEntity<AddressLineDTO> createAddressLine(@RequestBody AddressLineDTO addressLineDTO) {
		return patientcommandResourceApi.createAddressLineUsingPOST1(addressLineDTO);
	}

	@PutMapping("/address-lines")
	public ResponseEntity<AddressLineDTO> updateAddressLine(@RequestBody AddressLineDTO addressLineDTO) {
		return patientcommandResourceApi.updateAddressLineUsingPUT1(addressLineDTO);
	}

	// ---------------------Appointments-----------------------------
	@PostMapping("/appointments/additionalInformationRequest/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createAdditionalInformationRequest(
			@PathVariable("taskId") String taskId,
			@RequestBody AdditionalInformationRequest additionalInformationRequest) {
		return appointmentCommandResourceApi.additionalInformationRequestUsingPOST(taskId,
				additionalInformationRequest);
	}

	@PostMapping("/appointments/collectAdditionalDetails/{taskId}/{trackingId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createCollectAdditionalDetails(
			@PathVariable("taskId") String taskId, @PathVariable("trackingId") String trackingId,
			@RequestBody ConsultationDetails consultationDetails) {

		ConsultationInfoDTO consultationInfoDTO = new ConsultationInfoDTO();
		consultationInfoDTO.setAge(consultationDetails.getAge());
		consultationInfoDTO.setHeight(consultationDetails.getHeight());
		consultationInfoDTO.setWeight(Float.parseFloat(consultationDetails.getWeight() + ""));
		ConsultationInfoDTO result = consultationApi.createConsultationInfoUsingPOST(consultationInfoDTO).getBody();

		for (Syndrome syndrome : consultationDetails.getSymptoms()) {
			SymptomDTO symptomDTO = new SymptomDTO();
			symptomDTO.setConsultationInfoId(result.getId());
			symptomDTO.setNumberOfDaysSuffering(Integer.parseInt(syndrome.getNumberOfDaysSuffering()));
			symptomDTO.setRef(syndrome.getSymptomRef());
			symptomResourceApi.createSymptomUsingPOST(symptomDTO);
		}
		Appointment appointment = queryService.findAppointmentByTrackingId(trackingId).get();
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setAppointmentDateAndTime(appointment.getAppointmentDateAndTime());
		appointmentDTO.setConsultationInfoId(result.getId());
		appointmentDTO.setDoctorId(appointment.getDoctorId());
		appointmentDTO.setPatientId(appointment.getPatientId());
		appointmentDTO.setTimingId(appointment.getTiming().getId());
		appointmentDTO.setId(appointment.getId());
		appointmentDTO.setTrackingId(trackingId);
		appointmentDTO.setStatusId(3l);
		AppointmentDTO result1=appointmentCommandResourceApi.updateAppointmentUsingPUT(appointmentDTO).getBody();
		appointmentCommandResourceApi.updateAppointmentUsingPUT(result1);
		return appointmentCommandResourceApi.collectAdditionalDetailsUsingPOST(taskId, consultationDetails);
	}

	@PostMapping("/appointments/confirmPayment/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createConfirmPayment(
			@PathVariable("taskId") String taskId, @RequestBody PaymentConfirmationRequest paymentConfirmationRequest) {
		return appointmentCommandResourceApi.confirmPaymentUsingPOST(taskId, paymentConfirmationRequest);
	}

	@PostMapping("/appointments/confirmRegistration/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createConfirmRegistration(
			@PathVariable("taskId") String taskId) {
		return appointmentCommandResourceApi.confirmRegistrationUsingPOST(taskId);
	}

	@PostMapping("/appointments/initiateAppointment")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createInitiateAppointment(
			@RequestBody AppointmentRequest appointmentRequest) {
		ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> c = appointmentCommandResourceApi
				.initiateAppointmentUsingPOST(appointmentRequest);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + c.getBody());
		return c;
	}

	@PostMapping("/appointments/processPayment/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> createProcessPayment(
			@PathVariable("taskId") String taskId, @RequestBody ProcessPayment processPayment) {
		return appointmentCommandResourceApi.processPaymentUsingPOST(taskId, processPayment);
	}

	@PostMapping("/appointments/sendAppointmentRequest/{taskId}")
	public ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource> sendAppointmentRequest(
			@PathVariable String taskId, @RequestBody AppointmentConfirmationRequest appointmentConfirmationRequest) {
		return appointmentCommandResourceApi.sendAppointmentRequestUsingPOST(taskId, appointmentConfirmationRequest);
	}

	@GetMapping("/_search")
	public Page<TestDate> search() {
		return testDateSearchRepository.findAll(pageRequest);
	}

	@GetMapping("/doctorsearch")
	public Page<Doctor> doctorSearch() {
		return doctorSearchRepository.findAll(pageRequest);
	}

	@PostMapping("/payment/createOrder")
	public ResponseEntity<OrderResponse> createOrderPayment(@RequestBody OrderRequest orderRequest) {
		return razorpayCommandResource.createOrderUsingPOST(orderRequest);
	}

	/*
	 * @PostMapping("/appointments/processPayment/{taskId}") public
	 * ResponseEntity<com.bytatech.ayoos.client.appointment.model.CommandResource>
	 * createProcessPayment(@PathVariable("taskId") String taskId,@RequestBody
	 * ProcessPayment processPayment) { return
	 * appointmentCommandResourceApi.processPaymentUsingPOST(taskId,
	 * processPayment); }
	 */

	@PostMapping("/patients/modelToDto")
	public ResponseEntity<PatientDTO> modelToDto(@RequestBody Patient patient) {
		return patientResourceApi.modelToDtoUsingPOST(patient);

	}

	@PostMapping("/rating-review")
	public RatingReview createRatingAndReview(@RequestBody RatingReview ratingReview) {

		UserRatingDTO userRatingDTO = ratingReview.getRating();
		ReviewDTO reviewDTO = ratingReview.getReview();

		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + userRatingDTO + ">>>>>>>>>>>>>>>>>>>>>" + reviewDTO);
		if (userRatingDTO.getRating() != null) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>IF>>>>>>>>>>>>>>>>>>>>>>");
			DoctorDTO doctor = doctorResourceApi.getDoctorUsingGET(userRatingDTO.getDoctorId()).getBody();

			UserRating alreadyRatedUser = queryService.findRatingByDoctorIdAndPatientName(doctor.getDoctorId(),
					userRatingDTO.getUserName());

			log.info(">>>>>>>>>>>>>>>>>>>>>>alreadyRatedUser: >>>>>>>>>>>>>>>>>>>>>>>>" + alreadyRatedUser);

			if (alreadyRatedUser == null) {
				log.info("............create................");

				ResponseEntity<ReviewDTO> review = reviewResourceApi.createReviewUsingPOST(reviewDTO);

				ResponseEntity<UserRatingDTO> ratingDTO = userRatingResourceApi
						.createUserRatingUsingPOST(userRatingDTO);
				ratingReview.setRating(ratingDTO.getBody());
				ratingReview.setReview(review.getBody());
			} else {

				if (alreadyRatedUser.getId() != null) {
					log.info("....................UPDATE..............");

					userRatingDTO.setId(alreadyRatedUser.getId());

					log.info("................username:............" + userRatingDTO.getUserName()
							+ "..........doctorId..........." + doctor.getDoctorId() + "...........");

					Review alreadyreviewed = queryService.findReviewByDoctorIdAndPatientName(doctor.getDoctorId(),
							userRatingDTO.getUserName());

					log.info("...................   " + alreadyreviewed + "     ...............");

					reviewDTO.setId(alreadyreviewed.getId());

					ResponseEntity<ReviewDTO> review = reviewResourceApi.updateReviewUsingPUT(reviewDTO);

					ResponseEntity<UserRatingDTO> ratingDTO = userRatingResourceApi
							.updateUserRatingUsingPUT(userRatingDTO);

					ratingReview.setRating(ratingDTO.getBody());

					ratingReview.setReview(review.getBody());
				}
			}

		}
		return ratingReview;
	}

}
