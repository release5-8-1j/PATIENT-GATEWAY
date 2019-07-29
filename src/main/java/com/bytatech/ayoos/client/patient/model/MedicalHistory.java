 /*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytatech.ayoos.client.patient.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

import com.bytatech.ayoos.client.doctor.model.Doctor;

/**
 * TODO Provide a detailed description here 
 * @author MayaSanjeev
 * mayabytatech, maya.k.k@lxisoft.com
 */
@Document(indexName="medicalhistory")
public class MedicalHistory {

	private String diagnosisRef;
	
	private LocalDate date;
	
	private Doctor doctor;
	
	private List<String> prescriptionDmsUrl;
	
	private List<String> reportDmsUrl;
	
	List<StatusBar> statusBar;

	public String getDiagnosisRef() {
		return diagnosisRef;
	}

	public void setDiagnosisRef(String diagnosisRef) {
		this.diagnosisRef = diagnosisRef;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<String> getPrescriptionDmsUrl() {
		return prescriptionDmsUrl;
	}

	public void setPrescriptionDmsUrl(List<String> prescriptionDmsUrl) {
		this.prescriptionDmsUrl = prescriptionDmsUrl;
	}

	public List<String> getReportDmsUrl() {
		return reportDmsUrl;
	}

	public void setReportDmsUrl(List<String> reportDmsUrl) {
		this.reportDmsUrl = reportDmsUrl;
	}

	public List<StatusBar> getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(List<StatusBar> statusBar) {
		this.statusBar = statusBar;
	}

	@Override
	public String toString() {
		return "MedicalHistory [diagnosisRef=" + diagnosisRef + ", date=" + date + ", doctor=" + doctor
				+ ", prescriptionDmsUrl=" + prescriptionDmsUrl + ", reportDmsUrl=" + reportDmsUrl + ", statusBar="
				+ statusBar + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((diagnosisRef == null) ? 0 : diagnosisRef.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((prescriptionDmsUrl == null) ? 0 : prescriptionDmsUrl.hashCode());
		result = prime * result + ((reportDmsUrl == null) ? 0 : reportDmsUrl.hashCode());
		result = prime * result + ((statusBar == null) ? 0 : statusBar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalHistory other = (MedicalHistory) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (diagnosisRef == null) {
			if (other.diagnosisRef != null)
				return false;
		} else if (!diagnosisRef.equals(other.diagnosisRef))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (prescriptionDmsUrl == null) {
			if (other.prescriptionDmsUrl != null)
				return false;
		} else if (!prescriptionDmsUrl.equals(other.prescriptionDmsUrl))
			return false;
		if (reportDmsUrl == null) {
			if (other.reportDmsUrl != null)
				return false;
		} else if (!reportDmsUrl.equals(other.reportDmsUrl))
			return false;
		if (statusBar == null) {
			if (other.statusBar != null)
				return false;
		} else if (!statusBar.equals(other.statusBar))
			return false;
		return true;
	}
	
	
	
	
}
